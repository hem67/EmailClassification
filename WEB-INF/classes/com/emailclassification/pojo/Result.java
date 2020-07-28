package com.emailclassification.pojo;

import java.util.Map;

public class Result
{

   private Map<String, String> hmmResult;
   private Map<String, String> nbResult;

   public Map<String, String> getHmmResult()
   {
      return hmmResult;
   }

   public void setHmmResult(Map<String, String> hmmResult)
   {
      this.hmmResult = hmmResult;
   }

   public Map<String, String> getNbResult()
   {
      return nbResult;
   }

   public void setNbResult(Map<String, String> nbResult)
   {
      this.nbResult = nbResult;
   }

}
