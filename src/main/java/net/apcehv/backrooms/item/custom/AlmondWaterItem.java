package net.apcehv.backrooms.item.custom;


import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.entity.player.PlayerEntity;


public class AlmondWaterItem extends Item {

    protected final Random random = Random.create();

    private static FoodComponent getFoodComponent(){
        FoodComponent.Builder foodComponentBuilder = new FoodComponent.Builder();
        foodComponentBuilder.nutrition(3);
        foodComponentBuilder.saturationModifier(0.25f);
        foodComponentBuilder.statusEffect(
                new StatusEffectInstance(StatusEffects.REGENERATION, 60, 1), 1.0f);
        foodComponentBuilder.statusEffect(
                new StatusEffectInstance(StatusEffects.SATURATION, 30, 0), 0.01f);
        foodComponentBuilder.alwaysEdible();
        return foodComponentBuilder.build();
    }

    public AlmondWaterItem(Settings settings) {
        super(settings.food(getFoodComponent()).maxDamage(3));
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 20;
    }

    private void applyFoodEffects(World world, LivingEntity user, FoodComponent foodComponent){
        if (!world.isClient()) {
            for (FoodComponent.StatusEffectEntry statusEffectEntry : foodComponent.effects()) {
                if (this.random.nextFloat() < statusEffectEntry.probability()) {
                    user.addStatusEffect(statusEffectEntry.effect());
                }
            }
        }
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        FoodComponent foodComponent = stack.get(DataComponentTypes.FOOD);
        if (user instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) user;
            player.getHungerManager().eat(foodComponent);
            player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
        }
        applyFoodEffects(world, user, foodComponent);
        stack.damage(1, user, EquipmentSlot.MAINHAND);
        user.emitGameEvent(GameEvent.EAT);
        return stack;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public SoundEvent getDrinkSound() {
        return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
    }

}
