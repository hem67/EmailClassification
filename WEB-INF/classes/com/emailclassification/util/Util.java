package com.emailclassification.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Util
{

   public static String generateID()
   {
      SimpleDateFormat sdf = new SimpleDateFormat("ddMMhhmmsss");
      return sdf.format(new Timestamp(System.currentTimeMillis()));
   }

   public static String generateverificationcode()
   {
      SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmssSSS");
      return sdf.format(new Timestamp(System.currentTimeMillis()));
   }

}
