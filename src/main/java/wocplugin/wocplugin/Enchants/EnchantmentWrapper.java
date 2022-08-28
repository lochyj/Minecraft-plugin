package wocplugin.wocplugin.Enchants;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import wocplugin.wocplugin.handlers.EnchantmentHandler;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class EnchantmentWrapper {
    public static final Enchantment TELEPATHY = new EnchantmentHandler("telepathy", "Telepathy", 1);

    public static void register() {
        boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(TELEPATHY);
        if (!registered) {
            registerEnchantment(TELEPATHY);
        }
    }

    public static  void registerEnchantment(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field field = Enchantment.class.getDeclaredField("acceptingNew");
            field.setAccessible(true);
            field.set(null, true);
        } catch (IllegalArgumentException | NoSuchFieldException | IllegalAccessException e) {
            registered = false;
            e.printStackTrace();
        }

        if (registered) {
            Bukkit.getLogger().info("Registered enchantment " + enchantment.getName());
        }
    }

}
