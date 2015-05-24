package fluxedCrystals.util;

import java.text.DecimalFormat;

import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Keyboard;

public final class StringUtils {

    private static DecimalFormat formatter = new DecimalFormat("###,###");

    public static final String BLACK = EnumChatFormatting.BLACK.toString();
    public static final String BLUE = EnumChatFormatting.BLUE.toString();
    public static final String GREEN = EnumChatFormatting.GREEN.toString();
    public static final String TEAL = EnumChatFormatting.AQUA.toString();
    public static final String RED = EnumChatFormatting.DARK_RED.toString();
    public static final String PURPLE = EnumChatFormatting.DARK_PURPLE.toString();
    public static final String ORANGE = EnumChatFormatting.GOLD.toString();
    public static final String LIGHT_GRAY = EnumChatFormatting.GRAY.toString();
    public static final String GRAY = EnumChatFormatting.DARK_GRAY.toString();
    public static final String LIGHT_BLUE = EnumChatFormatting.DARK_BLUE.toString();
    public static final String BRIGHT_GREEN = EnumChatFormatting.GREEN.toString();
    public static final String BRIGHT_BLUE = EnumChatFormatting.BLUE.toString();
    public static final String LIGHT_RED = EnumChatFormatting.RED.toString();
    public static final String PINK = EnumChatFormatting.LIGHT_PURPLE.toString();
    public static final String YELLOW = EnumChatFormatting.YELLOW.toString();
    public static final String WHITE = EnumChatFormatting.WHITE.toString();

    public static final String OBFUSCATED = EnumChatFormatting.OBFUSCATED.toString();
    public static final String BOLD = EnumChatFormatting.BOLD.toString();
    public static final String STRIKETHROUGH = EnumChatFormatting.STRIKETHROUGH.toString();
    public static final String UNDERLINE = EnumChatFormatting.UNDERLINE.toString();
    public static final String ITALIC = EnumChatFormatting.ITALIC.toString();
    public static final String END = EnumChatFormatting.RESET.toString();

    public static boolean isAltKeyDown() {
        return Keyboard.isKeyDown(Keyboard.KEY_LMENU) || Keyboard.isKeyDown(Keyboard.KEY_RMENU);
    }

    public static boolean isControlKeyDown() {
        return Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL);
    }

    public static boolean isShiftKeyDown() {
        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
    }

    public static String getScaledNumber(int number) {
        return getScaledNumber(number, 2);
    }

    public static String getScaledNumber(int number, int minDigits) {
        String numString = "";

        int numMod = 10 * minDigits;

        if (number > 100000 * numMod) {
            numString += number / 1000000 + "M";
        } else if (number > 100 * numMod) {
            numString += number / 1000 + "k";
        } else {
            numString += number;
        }
        return numString;
    }

    public static String getFormattedNumber(int number) {
        return formatter.format(number);
    }

    public static String getChargeText(int charge, int total) {
        return ORANGE + localize("tooltip.charge") + ": " + StringUtils.getColoredPercent(charge, total) + getFormattedNumber(charge) + " / " + getFormattedNumber(total) + " RF";
    }

    public static String getEnergyUsageText(int usage) {
        return ORANGE + localize("tooltip.energyUsage") + ": " + LIGHT_GRAY + usage + " RF/t";
    }

    public static String getColoredPercent(int charge, int maxCharge) {
    	int percent = (int) (((double)charge/(double)maxCharge) * 100);
        if (percent > 70) {
            return BRIGHT_GREEN;
        } else if (percent > 40 && percent <= 70) {
            return YELLOW;
        } else if (percent > 10 && percent <= 40) {
            return ORANGE;
        } else {
            return LIGHT_RED;
        }
    }

    public static String getShiftText() {
        return YELLOW + "**" + localize("tooltip.holdShift") +"**";
    }

    public static boolean canShowDetails() {
        return true;
    }

    public static String localize(String unlocalized) {
        return localize(unlocalized, true);
    }

    public static String localize(String unlocalized, boolean prefix) {
        if (prefix) {
            return StatCollector.translateToLocal("FC." + unlocalized);
        }
        return StatCollector.translateToLocal(unlocalized);
    }

}