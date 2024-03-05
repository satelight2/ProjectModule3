package com.ra.util.font;

public class PrintForm {
    public static void warning(String string){
        System.out.println(ColorText.WARNING_RED+string+ColorText.RESET);
    }
    public static void attention(String string){
        System.out.println(ColorText.ATTENTION_YELOW+string+ColorText.RESET);
    }
    public static void success(String string){
        System.out.println(ColorText.SUCCESS_GREEN+string+ColorText.RESET);
    }
}
