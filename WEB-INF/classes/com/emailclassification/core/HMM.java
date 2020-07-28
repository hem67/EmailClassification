package com.emailclassification.core;

import static com.emailclassification.core.HMM.Status.HAM;
import static com.emailclassification.core.HMM.Status.SPAM;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.emailclassification.service.ObscureService;
import com.emailclassification.util.Constants;

public class HMM
{

	public static Map<String, Double> spamProbability = new HashMap<>();
	public static Map<String, Double> hamProbability = new HashMap<>();

	public HMM()
	{
		spamProbability.put(ObscureService.obscure("this isn't a scam"), 0.71);
		hamProbability.put(ObscureService.obscure("this isn't a scam"), 0.29);
		spamProbability.put(ObscureService.obscure("earn per week"), 0.61);
		hamProbability.put(ObscureService.obscure("earn per week"), 0.39);
		spamProbability.put(ObscureService.obscure("mail in order form"), 0.21);
		hamProbability.put(ObscureService.obscure("mail in order form"), 0.79);
		spamProbability.put(ObscureService.obscure("reverses"), 0.41);
		hamProbability.put(ObscureService.obscure("reverses"), 0.59);
		spamProbability.put(ObscureService.obscure("#1"), 0.51);
		hamProbability.put(ObscureService.obscure("#1"), 0.49);

		spamProbability.put(ObscureService.obscure("easy terms"), 0.51);
		hamProbability.put(ObscureService.obscure("easy terms"), 0.49);

		spamProbability.put(ObscureService.obscure("maintained"), 0.41);
		hamProbability.put(ObscureService.obscure("maintained"), 0.59);

		spamProbability.put(ObscureService.obscure("reverses aging"), 0.61);
		hamProbability.put(ObscureService.obscure("reverses aging"), 0.39);
		spamProbability.put(ObscureService.obscure("$$$"), 0.51);
		hamProbability.put(ObscureService.obscure("$$$"), 0.49);
		spamProbability.put(ObscureService.obscure("eliminate bad credit"), 0.51);
		hamProbability.put(ObscureService.obscure("eliminate bad credit"), 0.49);
		spamProbability.put(ObscureService.obscure("make $"), 0.51);
		hamProbability.put(ObscureService.obscure("make $"), 0.49);
		spamProbability.put(ObscureService.obscure("risk free"), 0.51);
		hamProbability.put(ObscureService.obscure("risk free"), 0.49);
		spamProbability.put(ObscureService.obscure("hidden assets"), 0.51);
		hamProbability.put(ObscureService.obscure("hidden assets"), 0.49);
		spamProbability.put(ObscureService.obscure("eliminate debt"), 0.51);
		hamProbability.put(ObscureService.obscure("eliminate debt"), 0.49);
		spamProbability.put(ObscureService.obscure("make money"), 0.51);
		hamProbability.put(ObscureService.obscure("make money"), 0.49);
		spamProbability.put(ObscureService.obscure("rolex"), 0.51);
		hamProbability.put(ObscureService.obscure("rolex"), 0.49);
		spamProbability.put(ObscureService.obscure("100% free"), 0.51);
		hamProbability.put(ObscureService.obscure("100% free"), 0.49);
		spamProbability.put(ObscureService.obscure("email harvest"), 0.51);
		hamProbability.put(ObscureService.obscure("email harvest"), 0.49);
		spamProbability.put(ObscureService.obscure("marketing"), 0.51);
		hamProbability.put(ObscureService.obscure("marketing"), 0.49);
		spamProbability.put(ObscureService.obscure("round the world"), 0.51);
		hamProbability.put(ObscureService.obscure("round the world"), 0.49);
		spamProbability.put(ObscureService.obscure("100% satisfied"), 0.51);
		hamProbability.put(ObscureService.obscure("100% satisfied"), 0.49);
		spamProbability.put(ObscureService.obscure("email marketing"), 0.51);
		hamProbability.put(ObscureService.obscure("email marketing"), 0.49);
		spamProbability.put(ObscureService.obscure("marketing solutions"), 0.51);
		hamProbability.put(ObscureService.obscure("marketing solutions"), 0.49);
		spamProbability.put(ObscureService.obscure("s 1618"), 0.51);
		hamProbability.put(ObscureService.obscure("s 1618"), 0.49);
		spamProbability.put(ObscureService.obscure("4u"), 0.51);
		hamProbability.put(ObscureService.obscure("4u"), 0.49);
		spamProbability.put(ObscureService.obscure("expect to earn"), 0.51);
		hamProbability.put(ObscureService.obscure("expect to earn"), 0.49);
		spamProbability.put(ObscureService.obscure("mass email"), 0.51);
		hamProbability.put(ObscureService.obscure("mass email"), 0.49);
		spamProbability.put(ObscureService.obscure("safeguard notice"), 0.51);
		hamProbability.put(ObscureService.obscure("safeguard notice"), 0.49);
		spamProbability.put(ObscureService.obscure("50% off"), 0.51);
		hamProbability.put(ObscureService.obscure("50% off"), 0.49);
		spamProbability.put(ObscureService.obscure("explode your business"), 0.51);
		hamProbability.put(ObscureService.obscure("explode your business"), 0.49);
		spamProbability.put(ObscureService.obscure("medicine"), 0.51);
		hamProbability.put(ObscureService.obscure("medicine"), 0.49);
		spamProbability.put(ObscureService.obscure("sale"), 0.51);
		hamProbability.put(ObscureService.obscure("sale"), 0.49);
		spamProbability.put(ObscureService.obscure("accept credit cards"), 0.51);
		hamProbability.put(ObscureService.obscure("accept credit cards"), 0.49);
		spamProbability.put(ObscureService.obscure("extra income"), 0.51);
		hamProbability.put(ObscureService.obscure("extra income"), 0.49);
		spamProbability.put(ObscureService.obscure("medium"), 0.51);
		hamProbability.put(ObscureService.obscure("medium"), 0.49);
		spamProbability.put(ObscureService.obscure("sales"), 0.51);
		hamProbability.put(ObscureService.obscure("sales"), 0.49);
		spamProbability.put(ObscureService.obscure("acceptance"), 0.51);
		hamProbability.put(ObscureService.obscure("acceptance"), 0.49);
		spamProbability.put(ObscureService.obscure("f r e e"), 0.51);
		hamProbability.put(ObscureService.obscure("f r e e"), 0.49);
		spamProbability.put(ObscureService.obscure("meet singles"), 0.51);
		hamProbability.put(ObscureService.obscure("meet singles"), 0.49);
		spamProbability.put(ObscureService.obscure("sample"), 0.51);
		hamProbability.put(ObscureService.obscure("sample"), 0.49);
		spamProbability.put(ObscureService.obscure("access"), 0.51);
		hamProbability.put(ObscureService.obscure("access"), 0.49);
		spamProbability.put(ObscureService.obscure("fantastic deal"), 0.51);
		hamProbability.put(ObscureService.obscure("fantastic deal"), 0.49);
		spamProbability.put(ObscureService.obscure("member"), 0.51);
		hamProbability.put(ObscureService.obscure("member"), 0.49);
		spamProbability.put(ObscureService.obscure("satisfaction"), 0.51);
		hamProbability.put(ObscureService.obscure("satisfaction"), 0.49);
		spamProbability.put(ObscureService.obscure("accordingly"), 0.51);
		hamProbability.put(ObscureService.obscure("accordingly"), 0.49);
		spamProbability.put(ObscureService.obscure("fast cash"), 0.51);
		hamProbability.put(ObscureService.obscure("fast cash"), 0.49);
		spamProbability.put(ObscureService.obscure("member stuff"), 0.51);
		hamProbability.put(ObscureService.obscure("member stuff"), 0.49);
		spamProbability.put(ObscureService.obscure("satisfaction guaranteed"), 0.51);
		hamProbability.put(ObscureService.obscure("satisfaction guaranteed"), 0.49);
		spamProbability.put(ObscureService.obscure("act now"), 0.51);
		hamProbability.put(ObscureService.obscure("act now"), 0.49);
		spamProbability.put(ObscureService.obscure("fast viagra delivery"), 0.51);
		hamProbability.put(ObscureService.obscure("fast viagra delivery"), 0.49);
		spamProbability.put(ObscureService.obscure("message contains"), 0.51);
		hamProbability.put(ObscureService.obscure("message contains"), 0.49);
		spamProbability.put(ObscureService.obscure("save $"), 0.51);
		hamProbability.put(ObscureService.obscure("save $"), 0.49);
		spamProbability.put(ObscureService.obscure("act now!"), 0.51);
		hamProbability.put(ObscureService.obscure("act now!"), 0.49);
		spamProbability.put(ObscureService.obscure("financial freedom"), 0.51);
		hamProbability.put(ObscureService.obscure("financial freedom"), 0.49);
		spamProbability.put(ObscureService.obscure("message contains disclaimer"), 0.51);
		hamProbability.put(ObscureService.obscure("message contains disclaimer"), 0.49);
		spamProbability.put(ObscureService.obscure("save big money"), 0.51);
		hamProbability.put(ObscureService.obscure("save big money"), 0.49);
		spamProbability.put(ObscureService.obscure("act now! don’t hesitate!"), 0.51);
		hamProbability.put(ObscureService.obscure("act now! don’t hesitate!"), 0.49);
		spamProbability.put(ObscureService.obscure("financially independent"), 0.51);
		hamProbability.put(ObscureService.obscure("financially independent"), 0.49);
		spamProbability.put(ObscureService.obscure("million"), 0.51);
		hamProbability.put(ObscureService.obscure("million"), 0.49);
		spamProbability.put(ObscureService.obscure("save up to"), 0.51);
		hamProbability.put(ObscureService.obscure("save up to"), 0.49);
		spamProbability.put(ObscureService.obscure("ad"), 0.51);
		hamProbability.put(ObscureService.obscure("ad"), 0.49);
		spamProbability.put(ObscureService.obscure("for free"), 0.51);
		hamProbability.put(ObscureService.obscure("for free"), 0.49);
		spamProbability.put(ObscureService.obscure("million dollars"), 0.51);
		hamProbability.put(ObscureService.obscure("million dollars"), 0.49);
		spamProbability.put(ObscureService.obscure("score with babes"), 0.51);
		hamProbability.put(ObscureService.obscure("score with babes"), 0.49);
		spamProbability.put(ObscureService.obscure("additional income"), 0.51);
		hamProbability.put(ObscureService.obscure("additional income"), 0.49);
		spamProbability.put(ObscureService.obscure("for instant access"), 0.51);
		hamProbability.put(ObscureService.obscure("for instant access"), 0.49);
		spamProbability.put(ObscureService.obscure("miracle"), 0.51);
		hamProbability.put(ObscureService.obscure("miracle"), 0.49);
		spamProbability.put(ObscureService.obscure("search engine listings"), 0.51);
		hamProbability.put(ObscureService.obscure("search engine listings"), 0.49);
		spamProbability.put(ObscureService.obscure("addresses on cd"), 0.51);
		hamProbability.put(ObscureService.obscure("addresses on cd"), 0.49);
		spamProbability.put(ObscureService.obscure("for just $ (some amount)"), 0.51);
		hamProbability.put(ObscureService.obscure("for just $ (some amount)"), 0.49);
		spamProbability.put(ObscureService.obscure("mlm"), 0.51);
		hamProbability.put(ObscureService.obscure("mlm"), 0.49);
		spamProbability.put(ObscureService.obscure("search engines"), 0.51);
		hamProbability.put(ObscureService.obscure("search engines"), 0.49);
		spamProbability.put(ObscureService.obscure("affordable"), 0.51);
		hamProbability.put(ObscureService.obscure("affordable"), 0.49);
		spamProbability.put(ObscureService.obscure("for just $xxx"), 0.51);
		hamProbability.put(ObscureService.obscure("for just $xxx"), 0.49);
		spamProbability.put(ObscureService.obscure("money"), 0.51);
		hamProbability.put(ObscureService.obscure("money"), 0.49);
		spamProbability.put(ObscureService.obscure("section 301"), 0.51);
		hamProbability.put(ObscureService.obscure("section 301"), 0.49);
		spamProbability.put(ObscureService.obscure("all natural"), 0.51);
		hamProbability.put(ObscureService.obscure("all natural"), 0.49);
		spamProbability.put(ObscureService.obscure("for only"), 0.51);
		hamProbability.put(ObscureService.obscure("for only"), 0.49);
		spamProbability.put(ObscureService.obscure("money back"), 0.51);
		hamProbability.put(ObscureService.obscure("money back"), 0.49);
		spamProbability.put(ObscureService.obscure("see for yourself"), 0.51);
		hamProbability.put(ObscureService.obscure("see for yourself"), 0.49);
		spamProbability.put(ObscureService.obscure("all new"), 0.51);
		hamProbability.put(ObscureService.obscure("all new"), 0.49);
		spamProbability.put(ObscureService.obscure("for you"), 0.51);
		hamProbability.put(ObscureService.obscure("for you"), 0.49);
		spamProbability.put(ObscureService.obscure("money making"), 0.51);
		hamProbability.put(ObscureService.obscure("money making"), 0.49);
		spamProbability.put(ObscureService.obscure("sent in compliance"), 0.51);
		hamProbability.put(ObscureService.obscure("sent in compliance"), 0.49);
		spamProbability.put(ObscureService.obscure("amazing"), 0.51);
		hamProbability.put(ObscureService.obscure("amazing"), 0.49);
		spamProbability.put(ObscureService.obscure("form"), 0.51);
		hamProbability.put(ObscureService.obscure("form"), 0.49);
		spamProbability.put(ObscureService.obscure("month trial offer"), 0.51);
		hamProbability.put(ObscureService.obscure("month trial offer"), 0.49);
		spamProbability.put(ObscureService.obscure("serious cash"), 0.51);
		hamProbability.put(ObscureService.obscure("serious cash"), 0.49);
		spamProbability.put(ObscureService.obscure("amazing stuff"), 0.51);
		hamProbability.put(ObscureService.obscure("amazing stuff"), 0.49);
		spamProbability.put(ObscureService.obscure("free"), 0.51);
		hamProbability.put(ObscureService.obscure("free"), 0.49);
		spamProbability.put(ObscureService.obscure("more internet traffic"), 0.51);
		hamProbability.put(ObscureService.obscure("more internet traffic"), 0.49);
		spamProbability.put(ObscureService.obscure("serious only"), 0.51);
		hamProbability.put(ObscureService.obscure("serious only"), 0.49);
		spamProbability.put(ObscureService.obscure("apply now"), 0.51);
		hamProbability.put(ObscureService.obscure("apply now"), 0.49);
		spamProbability.put(ObscureService.obscure("free access"), 0.51);
		hamProbability.put(ObscureService.obscure("free access"), 0.49);
		spamProbability.put(ObscureService.obscure("mortgage"), 0.51);
		hamProbability.put(ObscureService.obscure("mortgage"), 0.49);
		spamProbability.put(ObscureService.obscure("shopper"), 0.51);
		hamProbability.put(ObscureService.obscure("shopper"), 0.49);
		spamProbability.put(ObscureService.obscure("apply online"), 0.51);
		hamProbability.put(ObscureService.obscure("apply online"), 0.49);
		spamProbability.put(ObscureService.obscure("free cell phone"), 0.51);
		hamProbability.put(ObscureService.obscure("free cell phone"), 0.49);
		spamProbability.put(ObscureService.obscure("mortgage rates"), 0.51);
		hamProbability.put(ObscureService.obscure("mortgage rates"), 0.49);
		spamProbability.put(ObscureService.obscure("shopping spree"), 0.51);
		hamProbability.put(ObscureService.obscure("shopping spree"), 0.49);
		spamProbability.put(ObscureService.obscure("as seen on"), 0.51);
		hamProbability.put(ObscureService.obscure("as seen on"), 0.49);
		spamProbability.put(ObscureService.obscure("free consultation"), 0.51);
		hamProbability.put(ObscureService.obscure("free consultation"), 0.49);
		spamProbability.put(ObscureService.obscure("multi level marketing"), 0.51);
		hamProbability.put(ObscureService.obscure("multi level marketing"), 0.49);
		spamProbability.put(ObscureService.obscure("sign up free today"), 0.51);
		hamProbability.put(ObscureService.obscure("sign up free today"), 0.49);
		spamProbability.put(ObscureService.obscure("auto email removal"), 0.51);
		hamProbability.put(ObscureService.obscure("auto email removal"), 0.49);
		spamProbability.put(ObscureService.obscure("multi-level marketing"), 0.51);
		hamProbability.put(ObscureService.obscure("multi-level marketing"), 0.49);
		spamProbability.put(ObscureService.obscure("social security number"), 0.51);
		hamProbability.put(ObscureService.obscure("social security number"), 0.49);
		spamProbability.put(ObscureService.obscure("avoid"), 0.51);
		hamProbability.put(ObscureService.obscure("avoid"), 0.49);
		spamProbability.put(ObscureService.obscure("free dvd"), 0.51);
		hamProbability.put(ObscureService.obscure("free dvd"), 0.49);
		spamProbability.put(ObscureService.obscure("name brand"), 0.51);
		hamProbability.put(ObscureService.obscure("name brand"), 0.49);
		spamProbability.put(ObscureService.obscure("solution"), 0.51);
		hamProbability.put(ObscureService.obscure("solution"), 0.49);
		spamProbability.put(ObscureService.obscure("avoid bankruptcy"), 0.51);
		hamProbability.put(ObscureService.obscure("avoid bankruptcy"), 0.49);
		spamProbability.put(ObscureService.obscure("free gift"), 0.51);
		hamProbability.put(ObscureService.obscure("free gift"), 0.49);
		spamProbability.put(ObscureService.obscure("never"), 0.51);
		hamProbability.put(ObscureService.obscure("never"), 0.49);
		spamProbability.put(ObscureService.obscure("special promotion"), 0.51);
		hamProbability.put(ObscureService.obscure("special promotion"), 0.49);
		spamProbability.put(ObscureService.obscure("bargain"), 0.51);
		hamProbability.put(ObscureService.obscure("bargain"), 0.49);
		spamProbability.put(ObscureService.obscure("free grant money"), 0.51);
		hamProbability.put(ObscureService.obscure("free grant money"), 0.49);
		spamProbability.put(ObscureService.obscure("new customers only"), 0.51);
		hamProbability.put(ObscureService.obscure("new customers only"), 0.49);
		spamProbability.put(ObscureService.obscure("stainless steel"), 0.51);
		hamProbability.put(ObscureService.obscure("stainless steel"), 0.49);
		spamProbability.put(ObscureService.obscure("be amazed"), 0.51);
		hamProbability.put(ObscureService.obscure("be amazed"), 0.49);
		spamProbability.put(ObscureService.obscure("free hosting"), 0.51);
		hamProbability.put(ObscureService.obscure("free hosting"), 0.49);
		spamProbability.put(ObscureService.obscure("new domain extensions"), 0.51);
		hamProbability.put(ObscureService.obscure("new domain extensions"), 0.49);
		spamProbability.put(ObscureService.obscure("stock alert"), 0.51);
		hamProbability.put(ObscureService.obscure("stock alert"), 0.49);
		spamProbability.put(ObscureService.obscure("be your own boss"), 0.51);
		hamProbability.put(ObscureService.obscure("be your own boss"), 0.49);
		spamProbability.put(ObscureService.obscure("free info"), 0.51);
		hamProbability.put(ObscureService.obscure("free info"), 0.49);
		spamProbability.put(ObscureService.obscure("nigerian"), 0.51);
		hamProbability.put(ObscureService.obscure("nigerian"), 0.49);
		spamProbability.put(ObscureService.obscure("stock disclaimer statement"), 0.51);
		hamProbability.put(ObscureService.obscure("stock disclaimer statement"), 0.49);
		spamProbability.put(ObscureService.obscure("being a member"), 0.51);
		hamProbability.put(ObscureService.obscure("being a member"), 0.49);
		spamProbability.put(ObscureService.obscure("free installation"), 0.51);
		hamProbability.put(ObscureService.obscure("free installation"), 0.49);
		spamProbability.put(ObscureService.obscure("no age restrictions"), 0.51);
		hamProbability.put(ObscureService.obscure("no age restrictions"), 0.49);
		spamProbability.put(ObscureService.obscure("stock pick"), 0.51);
		hamProbability.put(ObscureService.obscure("stock pick"), 0.49);
		spamProbability.put(ObscureService.obscure("beneficiary"), 0.51);
		hamProbability.put(ObscureService.obscure("beneficiary"), 0.49);
		spamProbability.put(ObscureService.obscure("free instant"), 0.51);
		hamProbability.put(ObscureService.obscure("free instant"), 0.49);
		spamProbability.put(ObscureService.obscure("no catch"), 0.51);
		hamProbability.put(ObscureService.obscure("no catch"), 0.49);
		spamProbability.put(ObscureService.obscure("stop"), 0.51);
		hamProbability.put(ObscureService.obscure("stop"), 0.49);
		spamProbability.put(ObscureService.obscure("best price"), 0.51);
		hamProbability.put(ObscureService.obscure("best price"), 0.49);
		spamProbability.put(ObscureService.obscure("free investment"), 0.51);
		hamProbability.put(ObscureService.obscure("free investment"), 0.49);
		spamProbability.put(ObscureService.obscure("no claim forms"), 0.51);
		hamProbability.put(ObscureService.obscure("no claim forms"), 0.49);
		spamProbability.put(ObscureService.obscure("stop snoring"), 0.51);
		hamProbability.put(ObscureService.obscure("stop snoring"), 0.49);
		spamProbability.put(ObscureService.obscure("beverage"), 0.51);
		hamProbability.put(ObscureService.obscure("beverage"), 0.49);
		spamProbability.put(ObscureService.obscure("free leads"), 0.51);
		hamProbability.put(ObscureService.obscure("free leads"), 0.49);
		spamProbability.put(ObscureService.obscure("no cost"), 0.51);
		hamProbability.put(ObscureService.obscure("no cost"), 0.49);
		spamProbability.put(ObscureService.obscure("strong buy"), 0.51);
		hamProbability.put(ObscureService.obscure("strong buy"), 0.49);
		spamProbability.put(ObscureService.obscure("big bucks"), 0.51);
		hamProbability.put(ObscureService.obscure("big bucks"), 0.49);
		spamProbability.put(ObscureService.obscure("free membership"), 0.51);
		hamProbability.put(ObscureService.obscure("free membership"), 0.49);
		spamProbability.put(ObscureService.obscure("no credit check"), 0.51);
		hamProbability.put(ObscureService.obscure("no credit check"), 0.49);
		spamProbability.put(ObscureService.obscure("stuff on sale"), 0.51);
		hamProbability.put(ObscureService.obscure("stuff on sale"), 0.49);
		spamProbability.put(ObscureService.obscure("bill 1618"), 0.51);
		hamProbability.put(ObscureService.obscure("bill 1618"), 0.49);
		spamProbability.put(ObscureService.obscure("free money"), 0.51);
		hamProbability.put(ObscureService.obscure("free money"), 0.49);
		spamProbability.put(ObscureService.obscure("no disappointment"), 0.51);
		hamProbability.put(ObscureService.obscure("no disappointment"), 0.49);
		spamProbability.put(ObscureService.obscure("subject to cash"), 0.51);
		hamProbability.put(ObscureService.obscure("subject to cash"), 0.49);
		spamProbability.put(ObscureService.obscure("billing address"), 0.51);
		hamProbability.put(ObscureService.obscure("billing address"), 0.49);
		spamProbability.put(ObscureService.obscure("free offer"), 0.51);
		hamProbability.put(ObscureService.obscure("free offer"), 0.49);
		spamProbability.put(ObscureService.obscure("no experience"), 0.51);
		hamProbability.put(ObscureService.obscure("no experience"), 0.49);
		spamProbability.put(ObscureService.obscure("subject to credit"), 0.51);
		hamProbability.put(ObscureService.obscure("subject to credit"), 0.49);
		spamProbability.put(ObscureService.obscure("billion"), 0.51);
		hamProbability.put(ObscureService.obscure("billion"), 0.49);
		spamProbability.put(ObscureService.obscure("free preview"), 0.51);
		hamProbability.put(ObscureService.obscure("free preview"), 0.49);
		spamProbability.put(ObscureService.obscure("no fees"), 0.51);
		hamProbability.put(ObscureService.obscure("no fees"), 0.49);
		spamProbability.put(ObscureService.obscure("subscribe"), 0.51);
		hamProbability.put(ObscureService.obscure("subscribe"), 0.49);
		spamProbability.put(ObscureService.obscure("billion dollars"), 0.51);
		hamProbability.put(ObscureService.obscure("billion dollars"), 0.49);
		spamProbability.put(ObscureService.obscure("free priority mail"), 0.51);
		hamProbability.put(ObscureService.obscure("free priority mail"), 0.49);
		spamProbability.put(ObscureService.obscure("no gimmick"), 0.51);
		hamProbability.put(ObscureService.obscure("no gimmick"), 0.49);
		spamProbability.put(ObscureService.obscure("success"), 0.51);
		hamProbability.put(ObscureService.obscure("success"), 0.49);
		spamProbability.put(ObscureService.obscure("bonus"), 0.51);
		hamProbability.put(ObscureService.obscure("bonus"), 0.49);
		spamProbability.put(ObscureService.obscure("free quote"), 0.51);
		hamProbability.put(ObscureService.obscure("free quote"), 0.49);
		spamProbability.put(ObscureService.obscure("no hidden"), 0.51);
		hamProbability.put(ObscureService.obscure("no hidden"), 0.49);
		spamProbability.put(ObscureService.obscure("supplies are limited"), 0.51);
		hamProbability.put(ObscureService.obscure("supplies are limited"), 0.49);
		spamProbability.put(ObscureService.obscure("brand new pager"), 0.51);
		hamProbability.put(ObscureService.obscure("brand new pager"), 0.49);
		spamProbability.put(ObscureService.obscure("free sample"), 0.51);
		hamProbability.put(ObscureService.obscure("free sample"), 0.49);
		spamProbability.put(ObscureService.obscure("no inventory"), 0.51);
		hamProbability.put(ObscureService.obscure("no inventory"), 0.49);
		spamProbability.put(ObscureService.obscure("take action now"), 0.51);
		hamProbability.put(ObscureService.obscure("take action now"), 0.49);
		spamProbability.put(ObscureService.obscure("bulk email"), 0.51);
		hamProbability.put(ObscureService.obscure("bulk email"), 0.49);
		spamProbability.put(ObscureService.obscure("free trial"), 0.51);
		hamProbability.put(ObscureService.obscure("free trial"), 0.49);
		spamProbability.put(ObscureService.obscure("no investment"), 0.51);
		hamProbability.put(ObscureService.obscure("no investment"), 0.49);
		spamProbability.put(ObscureService.obscure("talks about hidden charges"), 0.51);
		hamProbability.put(ObscureService.obscure("talks about hidden charges"), 0.49);
		spamProbability.put(ObscureService.obscure("buy"), 0.51);
		hamProbability.put(ObscureService.obscure("buy"), 0.49);
		spamProbability.put(ObscureService.obscure("free website"), 0.51);
		hamProbability.put(ObscureService.obscure("free website"), 0.49);
		spamProbability.put(ObscureService.obscure("no medical exams"), 0.51);
		hamProbability.put(ObscureService.obscure("no medical exams"), 0.49);
		spamProbability.put(ObscureService.obscure("talks about prizes"), 0.51);
		hamProbability.put(ObscureService.obscure("talks about prizes"), 0.49);
		spamProbability.put(ObscureService.obscure("buy direct"), 0.51);
		hamProbability.put(ObscureService.obscure("buy direct"), 0.49);
		spamProbability.put(ObscureService.obscure("freedom"), 0.51);
		hamProbability.put(ObscureService.obscure("freedom"), 0.49);
		spamProbability.put(ObscureService.obscure("no middleman"), 0.51);
		hamProbability.put(ObscureService.obscure("no middleman"), 0.49);
		spamProbability.put(ObscureService.obscure("teen"), 0.51);
		hamProbability.put(ObscureService.obscure("teen"), 0.49);
		spamProbability.put(ObscureService.obscure("buying judgements"), 0.51);
		hamProbability.put(ObscureService.obscure("buying judgements"), 0.49);
		spamProbability.put(ObscureService.obscure("friend"), 0.51);
		hamProbability.put(ObscureService.obscure("friend"), 0.49);
		spamProbability.put(ObscureService.obscure("no obligation"), 0.51);
		hamProbability.put(ObscureService.obscure("no obligation"), 0.49);
		spamProbability.put(ObscureService.obscure("tells you it’s an ad"), 0.51);
		hamProbability.put(ObscureService.obscure("tells you it’s an ad"), 0.49);
		spamProbability.put(ObscureService.obscure("buying judgments"), 0.51);
		hamProbability.put(ObscureService.obscure("buying judgments"), 0.49);
		spamProbability.put(ObscureService.obscure("full refund"), 0.51);
		hamProbability.put(ObscureService.obscure("full refund"), 0.49);
		spamProbability.put(ObscureService.obscure("no purchase necessary"), 0.51);
		hamProbability.put(ObscureService.obscure("no purchase necessary"), 0.49);
		spamProbability.put(ObscureService.obscure("terms and conditions"), 0.51);
		hamProbability.put(ObscureService.obscure("terms and conditions"), 0.49);
		spamProbability.put(ObscureService.obscure("cable converter"), 0.51);
		hamProbability.put(ObscureService.obscure("cable converter"), 0.49);
		spamProbability.put(ObscureService.obscure("get"), 0.51);
		hamProbability.put(ObscureService.obscure("get"), 0.49);
		spamProbability.put(ObscureService.obscure("no questions asked"), 0.51);
		hamProbability.put(ObscureService.obscure("no questions asked"), 0.49);
		spamProbability.put(ObscureService.obscure("the best rates"), 0.51);
		hamProbability.put(ObscureService.obscure("the best rates"), 0.49);
		spamProbability.put(ObscureService.obscure("call"), 0.51);
		hamProbability.put(ObscureService.obscure("call"), 0.49);
		spamProbability.put(ObscureService.obscure("get it now"), 0.51);
		hamProbability.put(ObscureService.obscure("get it now"), 0.49);
		spamProbability.put(ObscureService.obscure("no selling"), 0.51);
		hamProbability.put(ObscureService.obscure("no selling"), 0.49);
		spamProbability.put(ObscureService.obscure("the following form"), 0.51);
		hamProbability.put(ObscureService.obscure("the following form"), 0.49);
		spamProbability.put(ObscureService.obscure("call free"), 0.51);
		hamProbability.put(ObscureService.obscure("call free"), 0.49);
		spamProbability.put(ObscureService.obscure("get out of debt"), 0.51);
		hamProbability.put(ObscureService.obscure("get out of debt"), 0.49);
		spamProbability.put(ObscureService.obscure("no strings attached"), 0.51);
		hamProbability.put(ObscureService.obscure("no strings attached"), 0.49);
		spamProbability.put(ObscureService.obscure("they keep your money ?no refund"), 0.51);
		hamProbability.put(ObscureService.obscure("they keep your money ?no refund"), 0.49);
		spamProbability.put(ObscureService.obscure("call now"), 0.51);
		hamProbability.put(ObscureService.obscure("call now"), 0.49);
		spamProbability.put(ObscureService.obscure("get paid"), 0.51);
		hamProbability.put(ObscureService.obscure("get paid"), 0.49);
		spamProbability.put(ObscureService.obscure("no-obligation"), 0.51);
		hamProbability.put(ObscureService.obscure("no-obligation"), 0.49);
		spamProbability.put(ObscureService.obscure("they’re just giving it away"), 0.51);
		hamProbability.put(ObscureService.obscure("they’re just giving it away"), 0.49);
		spamProbability.put(ObscureService.obscure("calling creditors"), 0.51);
		hamProbability.put(ObscureService.obscure("calling creditors"), 0.49);
		spamProbability.put(ObscureService.obscure("get started now"), 0.51);
		hamProbability.put(ObscureService.obscure("get started now"), 0.49);
		spamProbability.put(ObscureService.obscure("not intended"), 0.51);
		hamProbability.put(ObscureService.obscure("not intended"), 0.49);
		spamProbability.put(ObscureService.obscure("this isn’t junk"), 0.51);
		hamProbability.put(ObscureService.obscure("this isn’t junk"), 0.49);
		spamProbability.put(ObscureService.obscure("cancel at any time"), 0.51);
		hamProbability.put(ObscureService.obscure("cancel at any time"), 0.49);
		spamProbability.put(ObscureService.obscure("gift certificate"), 0.51);
		hamProbability.put(ObscureService.obscure("gift certificate"), 0.49);
		spamProbability.put(ObscureService.obscure("notspam"), 0.51);
		hamProbability.put(ObscureService.obscure("notspam"), 0.49);
		spamProbability.put(ObscureService.obscure("this isn’t spam"), 0.51);
		hamProbability.put(ObscureService.obscure("this isn’t spam"), 0.49);
		spamProbability.put(ObscureService.obscure("cannot be combined with any other offer"), 0.51);
		hamProbability.put(ObscureService.obscure("cannot be combined with any other offer"), 0.49);
		spamProbability.put(ObscureService.obscure("give it away"), 0.51);
		hamProbability.put(ObscureService.obscure("give it away"), 0.49);
		spamProbability.put(ObscureService.obscure("now"), 0.51);
		hamProbability.put(ObscureService.obscure("now"), 0.49);
		spamProbability.put(ObscureService.obscure("this won’t last"), 0.51);
		hamProbability.put(ObscureService.obscure("this won’t last"), 0.49);
		spamProbability.put(ObscureService.obscure("can’t live without"), 0.51);
		hamProbability.put(ObscureService.obscure("can’t live without"), 0.49);
		spamProbability.put(ObscureService.obscure("giving away"), 0.51);
		hamProbability.put(ObscureService.obscure("giving away"), 0.49);
		spamProbability.put(ObscureService.obscure("now only"), 0.51);
		hamProbability.put(ObscureService.obscure("now only"), 0.49);
		spamProbability.put(ObscureService.obscure("thousands"), 0.51);
		hamProbability.put(ObscureService.obscure("thousands"), 0.49);
		spamProbability.put(ObscureService.obscure("cards accepted"), 0.51);
		hamProbability.put(ObscureService.obscure("cards accepted"), 0.49);
		spamProbability.put(ObscureService.obscure("great offer"), 0.51);
		hamProbability.put(ObscureService.obscure("great offer"), 0.49);
		spamProbability.put(ObscureService.obscure("obligation"), 0.51);
		hamProbability.put(ObscureService.obscure("obligation"), 0.49);
		spamProbability.put(ObscureService.obscure("time limited"), 0.51);
		hamProbability.put(ObscureService.obscure("time limited"), 0.49);
		spamProbability.put(ObscureService.obscure("cash"), 0.51);
		hamProbability.put(ObscureService.obscure("cash"), 0.49);
		spamProbability.put(ObscureService.obscure("guarantee"), 0.51);
		hamProbability.put(ObscureService.obscure("guarantee"), 0.49);
		spamProbability.put(ObscureService.obscure("off shore"), 0.51);
		hamProbability.put(ObscureService.obscure("off shore"), 0.49);
		spamProbability.put(ObscureService.obscure("trial"), 0.51);
		hamProbability.put(ObscureService.obscure("trial"), 0.49);
		spamProbability.put(ObscureService.obscure("cash bonus"), 0.51);
		hamProbability.put(ObscureService.obscure("cash bonus"), 0.49);
		spamProbability.put(ObscureService.obscure("guaranteed"), 0.51);
		hamProbability.put(ObscureService.obscure("guaranteed"), 0.49);
		spamProbability.put(ObscureService.obscure("offer"), 0.51);
		hamProbability.put(ObscureService.obscure("offer"), 0.49);
		spamProbability.put(ObscureService.obscure("undisclosed recipient"), 0.51);
		hamProbability.put(ObscureService.obscure("undisclosed recipient"), 0.49);
		spamProbability.put(ObscureService.obscure("cashcashcash"), 0.51);
		hamProbability.put(ObscureService.obscure("cashcashcash"), 0.49);
		spamProbability.put(ObscureService.obscure("have you been turned down?"), 0.51);
		hamProbability.put(ObscureService.obscure("have you been turned down?"), 0.49);
		spamProbability.put(ObscureService.obscure("offer expires"), 0.51);
		hamProbability.put(ObscureService.obscure("offer expires"), 0.49);
		spamProbability.put(ObscureService.obscure("university diplomas"), 0.51);
		hamProbability.put(ObscureService.obscure("university diplomas"), 0.49);
		spamProbability.put(ObscureService.obscure("casino"), 0.51);
		hamProbability.put(ObscureService.obscure("casino"), 0.49);
		spamProbability.put(ObscureService.obscure("hello"), 0.51);
		hamProbability.put(ObscureService.obscure("hello"), 0.49);
		spamProbability.put(ObscureService.obscure("once in lifetime"), 0.51);
		hamProbability.put(ObscureService.obscure("once in lifetime"), 0.49);
		spamProbability.put(ObscureService.obscure("unlimited"), 0.51);
		hamProbability.put(ObscureService.obscure("unlimited"), 0.49);
		spamProbability.put(ObscureService.obscure("celebrity"), 0.51);
		hamProbability.put(ObscureService.obscure("celebrity"), 0.49);
		spamProbability.put(ObscureService.obscure("here"), 0.51);
		hamProbability.put(ObscureService.obscure("here"), 0.49);
		spamProbability.put(ObscureService.obscure("one hundred percent free"), 0.51);
		hamProbability.put(ObscureService.obscure("one hundred percent free"), 0.49);
		spamProbability.put(ObscureService.obscure("unsecured credit"), 0.51);
		hamProbability.put(ObscureService.obscure("unsecured credit"), 0.49);
		spamProbability.put(ObscureService.obscure("cell phone cancer scam"), 0.51);
		hamProbability.put(ObscureService.obscure("cell phone cancer scam"), 0.49);
		spamProbability.put(ObscureService.obscure("hidden"), 0.51);
		hamProbability.put(ObscureService.obscure("hidden"), 0.49);
		spamProbability.put(ObscureService.obscure("one hundred percent guaranteed"), 0.51);
		hamProbability.put(ObscureService.obscure("one hundred percent guaranteed"), 0.49);
		spamProbability.put(ObscureService.obscure("unsecured credit/debt"), 0.51);
		hamProbability.put(ObscureService.obscure("unsecured credit/debt"), 0.49);
		spamProbability.put(ObscureService.obscure("cents on the dollar"), 0.51);
		hamProbability.put(ObscureService.obscure("cents on the dollar"), 0.49);
		spamProbability.put(ObscureService.obscure("hidden assets"), 0.51);
		hamProbability.put(ObscureService.obscure("hidden assets"), 0.49);
		spamProbability.put(ObscureService.obscure("one time"), 0.51);
		hamProbability.put(ObscureService.obscure("one time"), 0.49);
		spamProbability.put(ObscureService.obscure("unsecured debt"), 0.51);
		hamProbability.put(ObscureService.obscure("unsecured debt"), 0.49);
		spamProbability.put(ObscureService.obscure("certified"), 0.51);
		hamProbability.put(ObscureService.obscure("certified"), 0.49);
		spamProbability.put(ObscureService.obscure("hidden charges"), 0.51);
		hamProbability.put(ObscureService.obscure("hidden charges"), 0.49);
		spamProbability.put(ObscureService.obscure("one time mailing"), 0.51);
		hamProbability.put(ObscureService.obscure("one time mailing"), 0.49);
		spamProbability.put(ObscureService.obscure("unsolicited"), 0.51);
		hamProbability.put(ObscureService.obscure("unsolicited"), 0.49);
		spamProbability.put(ObscureService.obscure("chance"), 0.51);
		hamProbability.put(ObscureService.obscure("chance"), 0.49);
		spamProbability.put(ObscureService.obscure("home"), 0.51);
		hamProbability.put(ObscureService.obscure("home"), 0.49);
		spamProbability.put(ObscureService.obscure("online biz opportunity"), 0.51);
		hamProbability.put(ObscureService.obscure("online biz opportunity"), 0.49);
		spamProbability.put(ObscureService.obscure("unsubscribe"), 0.51);
		hamProbability.put(ObscureService.obscure("unsubscribe"), 0.49);
		spamProbability.put(ObscureService.obscure("cheap"), 0.51);
		hamProbability.put(ObscureService.obscure("cheap"), 0.49);
		spamProbability.put(ObscureService.obscure("home based"), 0.51);
		hamProbability.put(ObscureService.obscure("home based"), 0.49);
		spamProbability.put(ObscureService.obscure("online degree"), 0.51);
		hamProbability.put(ObscureService.obscure("online degree"), 0.49);
		spamProbability.put(ObscureService.obscure("urgent"), 0.51);
		hamProbability.put(ObscureService.obscure("urgent"), 0.49);
		spamProbability.put(ObscureService.obscure("check"), 0.51);
		hamProbability.put(ObscureService.obscure("check"), 0.49);
		spamProbability.put(ObscureService.obscure("home employment"), 0.51);
		hamProbability.put(ObscureService.obscure("home employment"), 0.49);
		spamProbability.put(ObscureService.obscure("online marketing"), 0.51);
		hamProbability.put(ObscureService.obscure("online marketing"), 0.49);
		spamProbability.put(ObscureService.obscure("us dollars"), 0.51);
		hamProbability.put(ObscureService.obscure("us dollars"), 0.49);
		spamProbability.put(ObscureService.obscure("check or money order"), 0.51);
		hamProbability.put(ObscureService.obscure("check or money order"), 0.49);
		spamProbability.put(ObscureService.obscure("homebased business"), 0.51);
		hamProbability.put(ObscureService.obscure("homebased business"), 0.49);
		spamProbability.put(ObscureService.obscure("online pharmacy"), 0.51);
		hamProbability.put(ObscureService.obscure("online pharmacy"), 0.49);
		spamProbability.put(ObscureService.obscure("vacation"), 0.51);
		hamProbability.put(ObscureService.obscure("vacation"), 0.49);
		spamProbability.put(ObscureService.obscure("claims"), 0.51);
		hamProbability.put(ObscureService.obscure("claims"), 0.49);
		spamProbability.put(ObscureService.obscure("human growth hormone"), 0.51);
		hamProbability.put(ObscureService.obscure("human growth hormone"), 0.49);
		spamProbability.put(ObscureService.obscure("only"), 0.51);
		hamProbability.put(ObscureService.obscure("only"), 0.49);
		spamProbability.put(ObscureService.obscure("vacation offers"), 0.51);
		hamProbability.put(ObscureService.obscure("vacation offers"), 0.49);
		spamProbability.put(ObscureService.obscure("claims not to be selling anything"), 0.51);
		hamProbability.put(ObscureService.obscure("claims not to be selling anything"), 0.49);
		spamProbability.put(ObscureService.obscure("if only it were that easy"), 0.51);
		hamProbability.put(ObscureService.obscure("if only it were that easy"), 0.49);
		spamProbability.put(ObscureService.obscure("only $"), 0.51);
		hamProbability.put(ObscureService.obscure("only $"), 0.49);
		spamProbability.put(ObscureService.obscure("valium"), 0.51);
		hamProbability.put(ObscureService.obscure("valium"), 0.49);
		spamProbability.put(ObscureService.obscure("claims to be in accordance with some spam law"), 0.51);
		hamProbability.put(ObscureService.obscure("claims to be in accordance with some spam law"), 0.49);
		spamProbability.put(ObscureService.obscure("important information regarding"), 0.51);
		hamProbability.put(ObscureService.obscure("important information regarding"), 0.49);
		spamProbability.put(ObscureService.obscure("open"), 0.51);
		hamProbability.put(ObscureService.obscure("open"), 0.49);
		spamProbability.put(ObscureService.obscure("viagra"), 0.51);
		hamProbability.put(ObscureService.obscure("viagra"), 0.49);
		spamProbability.put(ObscureService.obscure("claims to be legal"), 0.51);
		hamProbability.put(ObscureService.obscure("claims to be legal"), 0.49);
		spamProbability.put(ObscureService.obscure("in accordance with laws"), 0.51);
		hamProbability.put(ObscureService.obscure("in accordance with laws"), 0.49);
		spamProbability.put(ObscureService.obscure("opportunity"), 0.51);
		hamProbability.put(ObscureService.obscure("opportunity"), 0.49);
		spamProbability.put(ObscureService.obscure("viagra and other drugs"), 0.51);
		hamProbability.put(ObscureService.obscure("viagra and other drugs"), 0.49);
		spamProbability.put(ObscureService.obscure("clearance"), 0.51);
		hamProbability.put(ObscureService.obscure("clearance"), 0.49);
		spamProbability.put(ObscureService.obscure("income"), 0.51);
		hamProbability.put(ObscureService.obscure("income"), 0.49);
		spamProbability.put(ObscureService.obscure("opt in"), 0.51);
		hamProbability.put(ObscureService.obscure("opt in"), 0.49);
		spamProbability.put(ObscureService.obscure("vicodin"), 0.51);
		hamProbability.put(ObscureService.obscure("vicodin"), 0.49);
		spamProbability.put(ObscureService.obscure("click"), 0.51);
		hamProbability.put(ObscureService.obscure("click"), 0.49);
		spamProbability.put(ObscureService.obscure("income from home"), 0.51);
		hamProbability.put(ObscureService.obscure("income from home"), 0.49);
		spamProbability.put(ObscureService.obscure("order"), 0.51);
		hamProbability.put(ObscureService.obscure("order"), 0.49);
		spamProbability.put(ObscureService.obscure("visit our website"), 0.51);
		hamProbability.put(ObscureService.obscure("visit our website"), 0.49);
		spamProbability.put(ObscureService.obscure("click below"), 0.51);
		hamProbability.put(ObscureService.obscure("click below"), 0.49);
		spamProbability.put(ObscureService.obscure("increase sales"), 0.51);
		hamProbability.put(ObscureService.obscure("increase sales"), 0.49);
		spamProbability.put(ObscureService.obscure("order now"), 0.51);
		hamProbability.put(ObscureService.obscure("order now"), 0.49);
		spamProbability.put(ObscureService.obscure("wants credit card"), 0.51);
		hamProbability.put(ObscureService.obscure("wants credit card"), 0.49);
		spamProbability.put(ObscureService.obscure("click here"), 0.51);
		hamProbability.put(ObscureService.obscure("click here"), 0.49);
		spamProbability.put(ObscureService.obscure("increase traffic"), 0.51);
		hamProbability.put(ObscureService.obscure("increase traffic"), 0.49);
		spamProbability.put(ObscureService.obscure("order shipped by"), 0.51);
		hamProbability.put(ObscureService.obscure("order shipped by"), 0.49);
		spamProbability.put(ObscureService.obscure("warranty"), 0.51);
		hamProbability.put(ObscureService.obscure("warranty"), 0.49);
		spamProbability.put(ObscureService.obscure("click to remove"), 0.51);
		hamProbability.put(ObscureService.obscure("click to remove"), 0.49);
		spamProbability.put(ObscureService.obscure("increase your sales"), 0.51);
		hamProbability.put(ObscureService.obscure("increase your sales"), 0.49);
		spamProbability.put(ObscureService.obscure("order status"), 0.51);
		hamProbability.put(ObscureService.obscure("order status"), 0.49);
		spamProbability.put(ObscureService.obscure("we hate spam"), 0.51);
		hamProbability.put(ObscureService.obscure("we hate spam"), 0.49);
		spamProbability.put(ObscureService.obscure("collect"), 0.51);
		hamProbability.put(ObscureService.obscure("collect"), 0.49);
		spamProbability.put(ObscureService.obscure("incredible deal"), 0.51);
		hamProbability.put(ObscureService.obscure("incredible deal"), 0.49);
		spamProbability.put(ObscureService.obscure("order today"), 0.51);
		hamProbability.put(ObscureService.obscure("order today"), 0.49);
		spamProbability.put(ObscureService.obscure("we honor all"), 0.51);
		hamProbability.put(ObscureService.obscure("we honor all"), 0.49);
		spamProbability.put(ObscureService.obscure("collect child support"), 0.51);
		hamProbability.put(ObscureService.obscure("collect child support"), 0.49);
		spamProbability.put(ObscureService.obscure("info you requested"), 0.51);
		hamProbability.put(ObscureService.obscure("info you requested"), 0.49);
		spamProbability.put(ObscureService.obscure("orders shipped by"), 0.51);
		hamProbability.put(ObscureService.obscure("orders shipped by"), 0.49);
		spamProbability.put(ObscureService.obscure("web traffic"), 0.51);
		hamProbability.put(ObscureService.obscure("web traffic"), 0.49);
		spamProbability.put(ObscureService.obscure("compare"), 0.51);
		hamProbability.put(ObscureService.obscure("compare"), 0.49);
		spamProbability.put(ObscureService.obscure("information you requested"), 0.51);
		hamProbability.put(ObscureService.obscure("information you requested"), 0.49);
		spamProbability.put(ObscureService.obscure("outstanding values"), 0.51);
		hamProbability.put(ObscureService.obscure("outstanding values"), 0.49);
		spamProbability.put(ObscureService.obscure("weekend getaway"), 0.51);
		hamProbability.put(ObscureService.obscure("weekend getaway"), 0.49);
		spamProbability.put(ObscureService.obscure("compare rates"), 0.51);
		hamProbability.put(ObscureService.obscure("compare rates"), 0.49);
		spamProbability.put(ObscureService.obscure("instant"), 0.51);
		hamProbability.put(ObscureService.obscure("instant"), 0.49);
		spamProbability.put(ObscureService.obscure("passwords"), 0.51);
		hamProbability.put(ObscureService.obscure("passwords"), 0.49);
		spamProbability.put(ObscureService.obscure("weight loss"), 0.51);
		hamProbability.put(ObscureService.obscure("weight loss"), 0.49);
		spamProbability.put(ObscureService.obscure("compete for your business"), 0.51);
		hamProbability.put(ObscureService.obscure("compete for your business"), 0.49);
		spamProbability.put(ObscureService.obscure("insurance"), 0.51);
		hamProbability.put(ObscureService.obscure("insurance"), 0.49);
		spamProbability.put(ObscureService.obscure("pennies a day"), 0.51);
		hamProbability.put(ObscureService.obscure("pennies a day"), 0.49);
		spamProbability.put(ObscureService.obscure("what are you waiting for?"), 0.51);
		hamProbability.put(ObscureService.obscure("what are you waiting for?"), 0.49);
		spamProbability.put(ObscureService.obscure("confidentially on all orders"), 0.51);
		hamProbability.put(ObscureService.obscure("confidentially on all orders"), 0.49);
		spamProbability.put(ObscureService.obscure("internet market"), 0.51);
		hamProbability.put(ObscureService.obscure("internet market"), 0.49);
		spamProbability.put(ObscureService.obscure("per day"), 0.51);
		hamProbability.put(ObscureService.obscure("per day"), 0.49);
		spamProbability.put(ObscureService.obscure("while supplies last"), 0.51);
		hamProbability.put(ObscureService.obscure("while supplies last"), 0.49);
		spamProbability.put(ObscureService.obscure("congratulations"), 0.51);
		hamProbability.put(ObscureService.obscure("congratulations"), 0.49);
		spamProbability.put(ObscureService.obscure("internet marketing"), 0.51);
		hamProbability.put(ObscureService.obscure("internet marketing"), 0.49);
		spamProbability.put(ObscureService.obscure("per week"), 0.51);
		hamProbability.put(ObscureService.obscure("per week"), 0.49);
		spamProbability.put(ObscureService.obscure("while you sleep"), 0.51);
		hamProbability.put(ObscureService.obscure("while you sleep"), 0.49);
		spamProbability.put(ObscureService.obscure("consolidate debt and credit"), 0.51);
		hamProbability.put(ObscureService.obscure("consolidate debt and credit"), 0.49);
		spamProbability.put(ObscureService.obscure("investment"), 0.51);
		hamProbability.put(ObscureService.obscure("investment"), 0.49);
		spamProbability.put(ObscureService.obscure("performance"), 0.51);
		hamProbability.put(ObscureService.obscure("performance"), 0.49);
		spamProbability.put(ObscureService.obscure("who really wins?"), 0.51);
		hamProbability.put(ObscureService.obscure("who really wins?"), 0.49);
		spamProbability.put(ObscureService.obscure("consolidate your debt"), 0.51);
		hamProbability.put(ObscureService.obscure("consolidate your debt"), 0.49);
		spamProbability.put(ObscureService.obscure("investment decision"), 0.51);
		hamProbability.put(ObscureService.obscure("investment decision"), 0.49);
		spamProbability.put(ObscureService.obscure("phone"), 0.51);
		hamProbability.put(ObscureService.obscure("phone"), 0.49);
		spamProbability.put(ObscureService.obscure("why pay more?"), 0.51);
		hamProbability.put(ObscureService.obscure("why pay more?"), 0.49);
		spamProbability.put(ObscureService.obscure("copy accurately"), 0.51);
		hamProbability.put(ObscureService.obscure("copy accurately"), 0.49);
		spamProbability.put(ObscureService.obscure("it’s effective"), 0.51);
		hamProbability.put(ObscureService.obscure("it’s effective"), 0.49);
		spamProbability.put(ObscureService.obscure("please read"), 0.51);
		hamProbability.put(ObscureService.obscure("please read"), 0.49);
		spamProbability.put(ObscureService.obscure("wife"), 0.51);
		hamProbability.put(ObscureService.obscure("wife"), 0.49);
		spamProbability.put(ObscureService.obscure("copy dvds"), 0.51);
		hamProbability.put(ObscureService.obscure("copy dvds"), 0.49);
		spamProbability.put(ObscureService.obscure("potential earnings"), 0.51);
		hamProbability.put(ObscureService.obscure("potential earnings"), 0.49);
		spamProbability.put(ObscureService.obscure("will not believe your eyes"), 0.51);
		hamProbability.put(ObscureService.obscure("will not believe your eyes"), 0.49);
		spamProbability.put(ObscureService.obscure("cost"), 0.51);
		hamProbability.put(ObscureService.obscure("cost"), 0.49);
		spamProbability.put(ObscureService.obscure("join millions"), 0.51);
		hamProbability.put(ObscureService.obscure("join millions"), 0.49);
		spamProbability.put(ObscureService.obscure("pre-approved"), 0.51);
		hamProbability.put(ObscureService.obscure("pre-approved"), 0.49);
		spamProbability.put(ObscureService.obscure("win"), 0.51);
		hamProbability.put(ObscureService.obscure("win"), 0.49);
		spamProbability.put(ObscureService.obscure("costs"), 0.51);
		hamProbability.put(ObscureService.obscure("costs"), 0.49);
		spamProbability.put(ObscureService.obscure("join millions of americans"), 0.51);
		hamProbability.put(ObscureService.obscure("join millions of americans"), 0.49);
		spamProbability.put(ObscureService.obscure("price"), 0.51);
		hamProbability.put(ObscureService.obscure("price"), 0.49);
		spamProbability.put(ObscureService.obscure("winner"), 0.51);
		hamProbability.put(ObscureService.obscure("winner"), 0.49);
		spamProbability.put(ObscureService.obscure("credit"), 0.51);
		hamProbability.put(ObscureService.obscure("credit"), 0.49);
		spamProbability.put(ObscureService.obscure("laser printer"), 0.51);
		hamProbability.put(ObscureService.obscure("laser printer"), 0.49);
		spamProbability.put(ObscureService.obscure("prices"), 0.51);
		hamProbability.put(ObscureService.obscure("prices"), 0.49);
		spamProbability.put(ObscureService.obscure("credit bureaus"), 0.51);
		hamProbability.put(ObscureService.obscure("credit bureaus"), 0.49);
		spamProbability.put(ObscureService.obscure("leave"), 0.51);
		hamProbability.put(ObscureService.obscure("leave"), 0.49);
		spamProbability.put(ObscureService.obscure("print form signature"), 0.51);
		hamProbability.put(ObscureService.obscure("print form signature"), 0.49);
		spamProbability.put(ObscureService.obscure("winning"), 0.51);
		hamProbability.put(ObscureService.obscure("winning"), 0.49);
		spamProbability.put(ObscureService.obscure("credit card offers"), 0.51);
		hamProbability.put(ObscureService.obscure("credit card offers"), 0.49);
		spamProbability.put(ObscureService.obscure("legal"), 0.51);
		hamProbability.put(ObscureService.obscure("legal"), 0.49);
		spamProbability.put(ObscureService.obscure("print out and fax"), 0.51);
		hamProbability.put(ObscureService.obscure("print out and fax"), 0.49);
		spamProbability.put(ObscureService.obscure("won"), 0.51);
		hamProbability.put(ObscureService.obscure("won"), 0.49);
		spamProbability.put(ObscureService.obscure("cures baldness"), 0.51);
		hamProbability.put(ObscureService.obscure("cures baldness"), 0.49);
		spamProbability.put(ObscureService.obscure("life"), 0.51);
		hamProbability.put(ObscureService.obscure("life"), 0.49);
		spamProbability.put(ObscureService.obscure("priority mail"), 0.51);
		hamProbability.put(ObscureService.obscure("priority mail"), 0.49);
		spamProbability.put(ObscureService.obscure("work at home"), 0.51);
		hamProbability.put(ObscureService.obscure("work at home"), 0.49);
		spamProbability.put(ObscureService.obscure("deal"), 0.51);
		hamProbability.put(ObscureService.obscure("deal"), 0.49);
		spamProbability.put(ObscureService.obscure("life insurance"), 0.51);
		hamProbability.put(ObscureService.obscure("life insurance"), 0.49);
		spamProbability.put(ObscureService.obscure("prize"), 0.51);
		hamProbability.put(ObscureService.obscure("prize"), 0.49);
		spamProbability.put(ObscureService.obscure("work from home"), 0.51);
		hamProbability.put(ObscureService.obscure("work from home"), 0.49);
		spamProbability.put(ObscureService.obscure("dear [email/friend/somebody]"), 0.51);
		hamProbability.put(ObscureService.obscure("dear [email/friend/somebody]"), 0.49);
		spamProbability.put(ObscureService.obscure("lifetime"), 0.51);
		hamProbability.put(ObscureService.obscure("lifetime"), 0.49);
		spamProbability.put(ObscureService.obscure("prizes"), 0.51);
		hamProbability.put(ObscureService.obscure("prizes"), 0.49);
		spamProbability.put(ObscureService.obscure("xanax"), 0.51);
		hamProbability.put(ObscureService.obscure("xanax"), 0.49);
		spamProbability.put(ObscureService.obscure("diagnostics"), 0.51);
		hamProbability.put(ObscureService.obscure("diagnostics"), 0.49);
		spamProbability.put(ObscureService.obscure("limited time"), 0.51);
		hamProbability.put(ObscureService.obscure("limited time"), 0.49);
		spamProbability.put(ObscureService.obscure("problem"), 0.51);
		hamProbability.put(ObscureService.obscure("problem"), 0.49);
		spamProbability.put(ObscureService.obscure("you are a winner!"), 0.51);
		hamProbability.put(ObscureService.obscure("you are a winner!"), 0.49);
		spamProbability.put(ObscureService.obscure("dig up dirt on friends"), 0.51);
		hamProbability.put(ObscureService.obscure("dig up dirt on friends"), 0.49);
		spamProbability.put(ObscureService.obscure("limited time offer"), 0.51);
		hamProbability.put(ObscureService.obscure("limited time offer"), 0.49);
		spamProbability.put(ObscureService.obscure("produced and sent out"), 0.51);
		hamProbability.put(ObscureService.obscure("produced and sent out"), 0.49);
		spamProbability.put(ObscureService.obscure("you have been selected"), 0.51);
		hamProbability.put(ObscureService.obscure("you have been selected"), 0.49);
		spamProbability.put(ObscureService.obscure("direct email"), 0.51);
		hamProbability.put(ObscureService.obscure("direct email"), 0.49);
		spamProbability.put(ObscureService.obscure("limited time only"), 0.51);
		hamProbability.put(ObscureService.obscure("limited time only"), 0.49);
		spamProbability.put(ObscureService.obscure("profits"), 0.51);
		hamProbability.put(ObscureService.obscure("profits"), 0.49);
		spamProbability.put(ObscureService.obscure("you’re a winner!"), 0.51);
		hamProbability.put(ObscureService.obscure("you’re a winner!"), 0.49);
		spamProbability.put(ObscureService.obscure("direct marketing"), 0.51);
		hamProbability.put(ObscureService.obscure("direct marketing"), 0.49);
		spamProbability.put(ObscureService.obscure("loans"), 0.51);
		hamProbability.put(ObscureService.obscure("loans"), 0.49);
		spamProbability.put(ObscureService.obscure("promise you"), 0.51);
		hamProbability.put(ObscureService.obscure("promise you"), 0.49);
		spamProbability.put(ObscureService.obscure("your income"), 0.51);
		hamProbability.put(ObscureService.obscure("your income"), 0.49);
		spamProbability.put(ObscureService.obscure("discount"), 0.51);
		hamProbability.put(ObscureService.obscure("discount"), 0.49);
		spamProbability.put(ObscureService.obscure("long distance phone offer"), 0.51);
		hamProbability.put(ObscureService.obscure("long distance phone offer"), 0.49);
		spamProbability.put(ObscureService.obscure("pure profits"), 0.51);
		hamProbability.put(ObscureService.obscure("pure profits"), 0.49);
		spamProbability.put(ObscureService.obscure("do it today"), 0.51);
		hamProbability.put(ObscureService.obscure("do it today"), 0.49);
		spamProbability.put(ObscureService.obscure("lose"), 0.51);
		hamProbability.put(ObscureService.obscure("lose"), 0.49);
		spamProbability.put(ObscureService.obscure("quote"), 0.51);
		hamProbability.put(ObscureService.obscure("quote"), 0.49);
		spamProbability.put(ObscureService.obscure("don’t delete"), 0.51);
		hamProbability.put(ObscureService.obscure("don’t delete"), 0.49);
		spamProbability.put(ObscureService.obscure("lose weight"), 0.51);
		hamProbability.put(ObscureService.obscure("lose weight"), 0.49);
		spamProbability.put(ObscureService.obscure("quotes"), 0.51);
		hamProbability.put(ObscureService.obscure("quotes"), 0.49);
		spamProbability.put(ObscureService.obscure("don’t hesitate"), 0.51);
		hamProbability.put(ObscureService.obscure("don’t hesitate"), 0.49);
		spamProbability.put(ObscureService.obscure("lose weight spam"), 0.51);
		hamProbability.put(ObscureService.obscure("lose weight spam"), 0.49);
		spamProbability.put(ObscureService.obscure("real thing"), 0.51);
		hamProbability.put(ObscureService.obscure("real thing"), 0.49);
		spamProbability.put(ObscureService.obscure("dormant"), 0.51);
		hamProbability.put(ObscureService.obscure("dormant"), 0.49);
		spamProbability.put(ObscureService.obscure("lower interest rate"), 0.51);
		hamProbability.put(ObscureService.obscure("lower interest rate"), 0.49);
		spamProbability.put(ObscureService.obscure("refinance"), 0.51);
		hamProbability.put(ObscureService.obscure("refinance"), 0.49);
		spamProbability.put(ObscureService.obscure("double your"), 0.51);
		hamProbability.put(ObscureService.obscure("double your"), 0.49);
		spamProbability.put(ObscureService.obscure("lower interest rates"), 0.51);
		hamProbability.put(ObscureService.obscure("lower interest rates"), 0.49);
		spamProbability.put(ObscureService.obscure("refinance home"), 0.51);
		hamProbability.put(ObscureService.obscure("refinance home"), 0.49);
		spamProbability.put(ObscureService.obscure("double your income"), 0.51);
		hamProbability.put(ObscureService.obscure("double your income"), 0.49);
		spamProbability.put(ObscureService.obscure("lower monthly payment"), 0.51);
		hamProbability.put(ObscureService.obscure("lower monthly payment"), 0.49);
		spamProbability.put(ObscureService.obscure("refinanced home "), 0.51);
		hamProbability.put(ObscureService.obscure("refinanced home "), 0.49);
		spamProbability.put(ObscureService.obscure("drastically reduced"), 0.51);
		hamProbability.put(ObscureService.obscure("drastically reduced"), 0.49);
		spamProbability.put(ObscureService.obscure("lower your mortgage rate"), 0.51);
		hamProbability.put(ObscureService.obscure("lower your mortgage rate"), 0.49);
		spamProbability.put(ObscureService.obscure("removal instructions "), 0.51);
		hamProbability.put(ObscureService.obscure("removal instructions "), 0.49);
		spamProbability.put(ObscureService.obscure("earn"), 0.51);
		hamProbability.put(ObscureService.obscure("earn"), 0.49);
		spamProbability.put(ObscureService.obscure("lowest insurance rates"), 0.51);
		hamProbability.put(ObscureService.obscure("lowest insurance rates"), 0.49);
		spamProbability.put(ObscureService.obscure("remove "), 0.51);
		hamProbability.put(ObscureService.obscure("remove "), 0.49);
		spamProbability.put(ObscureService.obscure("earn $"), 0.51);
		hamProbability.put(ObscureService.obscure("earn $"), 0.49);
		spamProbability.put(ObscureService.obscure("lowest price"), 0.51);
		hamProbability.put(ObscureService.obscure("lowest price"), 0.49);
		spamProbability.put(ObscureService.obscure("removes wrinkles "), 0.51);
		hamProbability.put(ObscureService.obscure("removes wrinkles "), 0.49);
		spamProbability.put(ObscureService.obscure("earn extra cash"), 0.51);
		hamProbability.put(ObscureService.obscure("earn extra cash"), 0.49);
		spamProbability.put(ObscureService.obscure("luxury car"), 0.51);
		hamProbability.put(ObscureService.obscure("luxury car"), 0.49);
		spamProbability.put(ObscureService.obscure("reserves the right"), 0.51);
		hamProbability.put(ObscureService.obscure("reserves the right"), 0.49);
		spamProbability.put(ObscureService.obscure("100%"), 0.51);
		hamProbability.put(ObscureService.obscure("100%"), 0.49);
		spamProbability.put(ObscureService.obscure("action"), 0.51);
		hamProbability.put(ObscureService.obscure("action"), 0.49);
		spamProbability.put(ObscureService.obscure("amazed"), 0.51);
		hamProbability.put(ObscureService.obscure("amazed"), 0.49);
		spamProbability.put(ObscureService.obscure("billing"), 0.51);
		hamProbability.put(ObscureService.obscure("billing"), 0.49);
		spamProbability.put(ObscureService.obscure("boss"), 0.51);
		hamProbability.put(ObscureService.obscure("boss"), 0.49);
		spamProbability.put(ObscureService.obscure("cancel"), 0.51);
		hamProbability.put(ObscureService.obscure("cancel"), 0.49);
		spamProbability.put(ObscureService.obscure("cures"), 0.51);
		hamProbability.put(ObscureService.obscure("cures"), 0.49);
		spamProbability.put(ObscureService.obscure("debt"), 0.51);
		hamProbability.put(ObscureService.obscure("debt"), 0.49);
		spamProbability.put(ObscureService.obscure("double your cash"), 0.51);
		hamProbability.put(ObscureService.obscure("double your cash"), 0.49);
		spamProbability.put(ObscureService.obscure("exclusive deal"), 0.51);
		hamProbability.put(ObscureService.obscure("exclusive deal"), 0.49);
		spamProbability.put(ObscureService.obscure("expire"), 0.51);
		hamProbability.put(ObscureService.obscure("expire"), 0.49);
		spamProbability.put(ObscureService.obscure("extra"), 0.51);
		hamProbability.put(ObscureService.obscure("extra"), 0.49);
		spamProbability.put(ObscureService.obscure("extra cash"), 0.51);
		hamProbability.put(ObscureService.obscure("extra cash"), 0.49);
		spamProbability.put(ObscureService.obscure("fantastic"), 0.51);
		hamProbability.put(ObscureService.obscure("fantastic"), 0.49);
		spamProbability.put(ObscureService.obscure("great"), 0.51);
		hamProbability.put(ObscureService.obscure("great"), 0.49);
		spamProbability.put(ObscureService.obscure("home based business"), 0.51);
		hamProbability.put(ObscureService.obscure("home based business"), 0.49);
		spamProbability.put(ObscureService.obscure("junk"), 0.51);
		hamProbability.put(ObscureService.obscure("junk"), 0.49);
		spamProbability.put(ObscureService.obscure("limited"), 0.51);
		hamProbability.put(ObscureService.obscure("limited"), 0.49);
		spamProbability.put(ObscureService.obscure("loan"), 0.51);
		hamProbability.put(ObscureService.obscure("loan"), 0.49);
		spamProbability.put(ObscureService.obscure("luxury"), 0.51);
		hamProbability.put(ObscureService.obscure("luxury"), 0.49);
		spamProbability.put(ObscureService.obscure("no hidden costs"), 0.51);
		hamProbability.put(ObscureService.obscure("no hidden costs"), 0.49);
		spamProbability.put(ObscureService.obscure("no interests"), 0.51);
		hamProbability.put(ObscureService.obscure("no interests"), 0.49);
		spamProbability.put(ObscureService.obscure("not junk"), 0.51);
		hamProbability.put(ObscureService.obscure("not junk"), 0.49);
		spamProbability.put(ObscureService.obscure("not spam"), 0.51);
		hamProbability.put(ObscureService.obscure("not spam"), 0.49);
		spamProbability.put(ObscureService.obscure("offshore"), 0.51);
		hamProbability.put(ObscureService.obscure("offshore"), 0.49);
		spamProbability.put(ObscureService.obscure("presently"), 0.51);
		hamProbability.put(ObscureService.obscure("presently"), 0.49);
		spamProbability.put(ObscureService.obscure("promise"), 0.51);
		hamProbability.put(ObscureService.obscure("promise"), 0.49);
		spamProbability.put(ObscureService.obscure("purchase"), 0.51);
		hamProbability.put(ObscureService.obscure("purchase"), 0.49);
		spamProbability.put(ObscureService.obscure("rates"), 0.51);
		hamProbability.put(ObscureService.obscure("rates"), 0.49);
		spamProbability.put(ObscureService.obscure("refund"), 0.51);
		hamProbability.put(ObscureService.obscure("refund"), 0.49);
		spamProbability.put(ObscureService.obscure("removal"), 0.51);
		hamProbability.put(ObscureService.obscure("removal"), 0.49);
		spamProbability.put(ObscureService.obscure("removal instructions"), 0.51);
		hamProbability.put(ObscureService.obscure("removal instructions"), 0.49);
		spamProbability.put(ObscureService.obscure("remove"), 0.51);
		hamProbability.put(ObscureService.obscure("remove"), 0.49);
		spamProbability.put(ObscureService.obscure("removes wrinkles"), 0.51);
		hamProbability.put(ObscureService.obscure("removes wrinkles"), 0.49);
		spamProbability.put(ObscureService.obscure("request"), 0.51);
		hamProbability.put(ObscureService.obscure("request"), 0.49);
		spamProbability.put(ObscureService.obscure("requires initial investment"), 0.51);
		hamProbability.put(ObscureService.obscure("requires initial investment"), 0.49);
		spamProbability.put(ObscureService.obscure("score"), 0.51);
		hamProbability.put(ObscureService.obscure("score"), 0.49);
		spamProbability.put(ObscureService.obscure("serious"), 0.51);
		hamProbability.put(ObscureService.obscure("serious"), 0.49);
		spamProbability.put(ObscureService.obscure("spam"), 0.51);
		hamProbability.put(ObscureService.obscure("spam"), 0.49);
		spamProbability.put(ObscureService.obscure("supplies"), 0.51);
		hamProbability.put(ObscureService.obscure("supplies"), 0.49);
		spamProbability.put(ObscureService.obscure("take action"), 0.51);
		hamProbability.put(ObscureService.obscure("take action"), 0.49);
		spamProbability.put(ObscureService.obscure("terms"), 0.51);
		hamProbability.put(ObscureService.obscure("terms"), 0.49);
		spamProbability.put(ObscureService.obscure("they keep your money ?no refund!"), 0.51);
		hamProbability.put(ObscureService.obscure("they keep your money ?no refund!"), 0.49);
		spamProbability.put(ObscureService.obscure("traffic"), 0.51);
		hamProbability.put(ObscureService.obscure("traffic"), 0.49);
		spamProbability.put(ObscureService.obscure("weight"), 0.51);
		hamProbability.put(ObscureService.obscure("weight"), 0.49);
		spamProbability.put(ObscureService.obscure("what’s keeping you?"), 0.51);
		hamProbability.put(ObscureService.obscure("what’s keeping you?"), 0.49);
		spamProbability.put(ObscureService.obscure(" as seen on"), 0.51);
		hamProbability.put(ObscureService.obscure(" as seen on"), 0.49);
		spamProbability.put(ObscureService.obscure("dig up dirt on friends "), 0.51);
		hamProbability.put(ObscureService.obscure("dig up dirt on friends "), 0.49);
		spamProbability.put(ObscureService.obscure("meet singles "), 0.51);
		hamProbability.put(ObscureService.obscure("meet singles "), 0.49);
		spamProbability.put(ObscureService.obscure("  additional income"), 0.51);
		hamProbability.put(ObscureService.obscure("  additional income"), 0.49);
		spamProbability.put(ObscureService.obscure("earn per week "), 0.51);
		hamProbability.put(ObscureService.obscure("earn per week "), 0.49);
		spamProbability.put(ObscureService.obscure("expect to earn "), 0.51);
		hamProbability.put(ObscureService.obscure("expect to earn "), 0.49);
		spamProbability.put(ObscureService.obscure("opportunity "), 0.51);
		hamProbability.put(ObscureService.obscure("opportunity "), 0.49);
		spamProbability.put(ObscureService.obscure("potential earnings "), 0.51);
		hamProbability.put(ObscureService.obscure("potential earnings "), 0.49);
		spamProbability.put(ObscureService.obscure("while you sleep "), 0.51);
		hamProbability.put(ObscureService.obscure("while you sleep "), 0.49);
		spamProbability.put(ObscureService.obscure("work at home "), 0.51);
		hamProbability.put(ObscureService.obscure("work at home "), 0.49);
		spamProbability.put(ObscureService.obscure("big bucks "), 0.51);
		hamProbability.put(ObscureService.obscure("big bucks "), 0.49);
		spamProbability.put(ObscureService.obscure("cash bonus "), 0.51);
		hamProbability.put(ObscureService.obscure("cash bonus "), 0.49);
		spamProbability.put(ObscureService.obscure("cents on the dollar "), 0.51);
		hamProbability.put(ObscureService.obscure("cents on the dollar "), 0.49);
		spamProbability.put(ObscureService.obscure("credit bureaus "), 0.51);
		hamProbability.put(ObscureService.obscure("credit bureaus "), 0.49);
		spamProbability.put(ObscureService.obscure("easy terms "), 0.51);
		hamProbability.put(ObscureService.obscure("easy terms "), 0.49);
		spamProbability.put(ObscureService.obscure("hidden assets "), 0.51);
		hamProbability.put(ObscureService.obscure("hidden assets "), 0.49);
		spamProbability.put(ObscureService.obscure("insurance "), 0.51);
		hamProbability.put(ObscureService.obscure("insurance "), 0.49);
		spamProbability.put(ObscureService.obscure("lowest price "), 0.51);
		hamProbability.put(ObscureService.obscure("lowest price "), 0.49);
		spamProbability.put(ObscureService.obscure("mortgage rates "), 0.51);
		hamProbability.put(ObscureService.obscure("mortgage rates "), 0.49);
		spamProbability.put(ObscureService.obscure("no fees "), 0.51);
		hamProbability.put(ObscureService.obscure("no fees "), 0.49);
		spamProbability.put(ObscureService.obscure("one hundred percent free "), 0.51);
		hamProbability.put(ObscureService.obscure("one hundred percent free "), 0.49);
		spamProbability.put(ObscureService.obscure("only $ "), 0.51);
		hamProbability.put(ObscureService.obscure("only $ "), 0.49);
		spamProbability.put(ObscureService.obscure("pennies a day "), 0.51);
		hamProbability.put(ObscureService.obscure("pennies a day "), 0.49);
		spamProbability.put(ObscureService.obscure("profits "), 0.51);
		hamProbability.put(ObscureService.obscure("profits "), 0.49);
		spamProbability.put(ObscureService.obscure("pure profit"), 0.51);
		hamProbability.put(ObscureService.obscure("pure profit"), 0.49);
		spamProbability.put(ObscureService.obscure("they keep your money -- no refund! "), 0.51);
		hamProbability.put(ObscureService.obscure("they keep your money -- no refund! "), 0.49);
		spamProbability.put(ObscureService.obscure("why pay more? "), 0.51);
		hamProbability.put(ObscureService.obscure("why pay more? "), 0.49);
		spamProbability.put(ObscureService.obscure("check or money order "), 0.51);
		hamProbability.put(ObscureService.obscure("check or money order "), 0.49);
		spamProbability.put(ObscureService.obscure("credit card offers "), 0.51);
		hamProbability.put(ObscureService.obscure("credit card offers "), 0.49);
		spamProbability.put(ObscureService.obscure("full refund "), 0.51);
		hamProbability.put(ObscureService.obscure("full refund "), 0.49);
		spamProbability.put(ObscureService.obscure("investment decision "), 0.51);
		hamProbability.put(ObscureService.obscure("investment decision "), 0.49);
		spamProbability.put(ObscureService.obscure("no credit check "), 0.51);
		hamProbability.put(ObscureService.obscure("no credit check "), 0.49);
		spamProbability.put(ObscureService.obscure("stock alert "), 0.51);
		hamProbability.put(ObscureService.obscure("stock alert "), 0.49);
		spamProbability.put(ObscureService.obscure("stock disclaimer statement "), 0.51);
		hamProbability.put(ObscureService.obscure("stock disclaimer statement "), 0.49);
		spamProbability.put(ObscureService.obscure("stock pick "), 0.51);
		hamProbability.put(ObscureService.obscure("stock pick "), 0.49);
		spamProbability.put(ObscureService.obscure("eliminate bad credit "), 0.51);
		hamProbability.put(ObscureService.obscure("eliminate bad credit "), 0.49);
		spamProbability.put(ObscureService.obscure("get paid "), 0.51);
		hamProbability.put(ObscureService.obscure("get paid "), 0.49);
		spamProbability.put(ObscureService.obscure("lower monthly payment "), 0.51);
		hamProbability.put(ObscureService.obscure("lower monthly payment "), 0.49);
		spamProbability.put(ObscureService.obscure("refinance home "), 0.51);
		hamProbability.put(ObscureService.obscure("refinance home "), 0.49);
		spamProbability.put(ObscureService.obscure("social security number "), 0.51);
		hamProbability.put(ObscureService.obscure("social security number "), 0.49);
		spamProbability.put(ObscureService.obscure("dear [email/friend/somebody] "), 0.51);
		hamProbability.put(ObscureService.obscure("dear [email/friend/somebody] "), 0.49);
		spamProbability.put(ObscureService.obscure("auto email removal "), 0.51);
		hamProbability.put(ObscureService.obscure("auto email removal "), 0.49);
		spamProbability.put(ObscureService.obscure("click to remove "), 0.51);
		hamProbability.put(ObscureService.obscure("click to remove "), 0.49);
		spamProbability.put(ObscureService.obscure("direct email "), 0.51);
		hamProbability.put(ObscureService.obscure("direct email "), 0.49);
		spamProbability.put(ObscureService.obscure("direct marketing "), 0.51);
		hamProbability.put(ObscureService.obscure("direct marketing "), 0.49);
		spamProbability.put(ObscureService.obscure("email harvest "), 0.51);
		hamProbability.put(ObscureService.obscure("email harvest "), 0.49);
		spamProbability.put(ObscureService.obscure("email marketing "), 0.51);
		hamProbability.put(ObscureService.obscure("email marketing "), 0.49);
		spamProbability.put(ObscureService.obscure("increase sales "), 0.51);
		hamProbability.put(ObscureService.obscure("increase sales "), 0.49);
		spamProbability.put(ObscureService.obscure("increase traffic "), 0.51);
		hamProbability.put(ObscureService.obscure("increase traffic "), 0.49);
		spamProbability.put(ObscureService.obscure("marketing solutions "), 0.51);
		hamProbability.put(ObscureService.obscure("marketing solutions "), 0.49);
		spamProbability.put(ObscureService.obscure("mass email "), 0.51);
		hamProbability.put(ObscureService.obscure("mass email "), 0.49);
		spamProbability.put(ObscureService.obscure("one time mailing "), 0.51);
		hamProbability.put(ObscureService.obscure("one time mailing "), 0.49);
		spamProbability.put(ObscureService.obscure("this isn't junk "), 0.51);
		hamProbability.put(ObscureService.obscure("this isn't junk "), 0.49);
		spamProbability.put(ObscureService.obscure("this isn't spam "), 0.51);
		hamProbability.put(ObscureService.obscure("this isn't spam "), 0.49);
		spamProbability.put(ObscureService.obscure("we hate spam "), 0.51);
		hamProbability.put(ObscureService.obscure("we hate spam "), 0.49);
		spamProbability.put(ObscureService.obscure("will not believe your eyes "), 0.51);
		hamProbability.put(ObscureService.obscure("will not believe your eyes "), 0.49);
		spamProbability.put(ObscureService.obscure("cures baldness "), 0.51);
		hamProbability.put(ObscureService.obscure("cures baldness "), 0.49);
		spamProbability.put(ObscureService.obscure("lose weight spam "), 0.51);
		hamProbability.put(ObscureService.obscure("lose weight spam "), 0.49);
		spamProbability.put(ObscureService.obscure("online pharmacy "), 0.51);
		hamProbability.put(ObscureService.obscure("online pharmacy "), 0.49);
		spamProbability.put(ObscureService.obscure("stop snoring "), 0.51);
		hamProbability.put(ObscureService.obscure("stop snoring "), 0.49);
		spamProbability.put(ObscureService.obscure("billion dollars "), 0.51);
		hamProbability.put(ObscureService.obscure("billion dollars "), 0.49);
		spamProbability.put(ObscureService.obscure("join millions of americans "), 0.51);
		hamProbability.put(ObscureService.obscure("join millions of americans "), 0.49);
		spamProbability.put(ObscureService.obscure("one hundred percent guaranteed "), 0.51);
		hamProbability.put(ObscureService.obscure("one hundred percent guaranteed "), 0.49);
		spamProbability.put(ObscureService.obscure("billing address "), 0.51);
		hamProbability.put(ObscureService.obscure("billing address "), 0.49);
		spamProbability.put(ObscureService.obscure("cannot be combined with any other offer "), 0.51);
		hamProbability.put(ObscureService.obscure("cannot be combined with any other offer "), 0.49);
		spamProbability.put(ObscureService.obscure("confidentially on all orders "), 0.51);
		hamProbability.put(ObscureService.obscure("confidentially on all orders "), 0.49);
		spamProbability.put(ObscureService.obscure("financial freedom "), 0.51);
		hamProbability.put(ObscureService.obscure("financial freedom "), 0.49);
		spamProbability.put(ObscureService.obscure("guarantee "), 0.51);
		hamProbability.put(ObscureService.obscure("guarantee "), 0.49);
		spamProbability.put(ObscureService.obscure("have you been turned down? "), 0.51);
		hamProbability.put(ObscureService.obscure("have you been turned down? "), 0.49);
		spamProbability.put(ObscureService.obscure("mail in order form "), 0.51);
		hamProbability.put(ObscureService.obscure("mail in order form "), 0.49);
		spamProbability.put(ObscureService.obscure("name brand "), 0.51);
		hamProbability.put(ObscureService.obscure("name brand "), 0.49);
		spamProbability.put(ObscureService.obscure("nigerian "), 0.51);
		hamProbability.put(ObscureService.obscure("nigerian "), 0.49);
		spamProbability.put(ObscureService.obscure("no age restrictions "), 0.51);
		hamProbability.put(ObscureService.obscure("no age restrictions "), 0.49);
		spamProbability.put(ObscureService.obscure("no catch "), 0.51);
		hamProbability.put(ObscureService.obscure("no catch "), 0.49);
		spamProbability.put(ObscureService.obscure("no claim forms "), 0.51);
		hamProbability.put(ObscureService.obscure("no claim forms "), 0.49);
		spamProbability.put(ObscureService.obscure("no disappointment "), 0.51);
		hamProbability.put(ObscureService.obscure("no disappointment "), 0.49);
		spamProbability.put(ObscureService.obscure("no experience "), 0.51);
		hamProbability.put(ObscureService.obscure("no experience "), 0.49);
		spamProbability.put(ObscureService.obscure("no gimmick "), 0.51);
		hamProbability.put(ObscureService.obscure("no gimmick "), 0.49);
		spamProbability.put(ObscureService.obscure("no inventory "), 0.51);
		hamProbability.put(ObscureService.obscure("no inventory "), 0.49);
		spamProbability.put(ObscureService.obscure("not intended "), 0.51);
		hamProbability.put(ObscureService.obscure("not intended "), 0.49);
		spamProbability.put(ObscureService.obscure("produced and sent out "), 0.51);
		hamProbability.put(ObscureService.obscure("produced and sent out "), 0.49);
		spamProbability.put(ObscureService.obscure("shopping spree "), 0.51);
		hamProbability.put(ObscureService.obscure("shopping spree "), 0.49);
		spamProbability.put(ObscureService.obscure("we honor all "), 0.51);
		hamProbability.put(ObscureService.obscure("we honor all "), 0.49);
		spamProbability.put(ObscureService.obscure("weekend getaway "), 0.51);
		hamProbability.put(ObscureService.obscure("weekend getaway "), 0.49);
		spamProbability.put(ObscureService.obscure("what are you waiting for? "), 0.51);
		hamProbability.put(ObscureService.obscure("what are you waiting for? "), 0.49);
		spamProbability.put(ObscureService.obscure("who really wins? "), 0.51);
		hamProbability.put(ObscureService.obscure("who really wins? "), 0.49);
		spamProbability.put(ObscureService.obscure("winner "), 0.51);
		hamProbability.put(ObscureService.obscure("winner "), 0.49);
		spamProbability.put(ObscureService.obscure("winning "), 0.51);
		hamProbability.put(ObscureService.obscure("winning "), 0.49);
		spamProbability.put(ObscureService.obscure("you have been selected "), 0.51);
		hamProbability.put(ObscureService.obscure("you have been selected "), 0.49);
		spamProbability.put(ObscureService.obscure(""), 0.51);
		hamProbability.put(ObscureService.obscure(""), 0.49);
		spamProbability.put(ObscureService.obscure("cancel at any time "), 0.51);
		hamProbability.put(ObscureService.obscure("cancel at any time "), 0.49);
		spamProbability.put(ObscureService.obscure("copy accurately "), 0.51);
		hamProbability.put(ObscureService.obscure("copy accurately "), 0.49);
		spamProbability.put(ObscureService.obscure("print out and fax "), 0.51);
		hamProbability.put(ObscureService.obscure("print out and fax "), 0.49);
		spamProbability.put(ObscureService.obscure("sign up free today "), 0.51);
		hamProbability.put(ObscureService.obscure("sign up free today "), 0.49);
		spamProbability.put(ObscureService.obscure("free access "), 0.51);
		hamProbability.put(ObscureService.obscure("free access "), 0.49);
		spamProbability.put(ObscureService.obscure("free cell phone "), 0.51);
		hamProbability.put(ObscureService.obscure("free cell phone "), 0.49);
		spamProbability.put(ObscureService.obscure("free consultation "), 0.51);
		hamProbability.put(ObscureService.obscure("free consultation "), 0.49);
		spamProbability.put(ObscureService.obscure("free dvd "), 0.51);
		hamProbability.put(ObscureService.obscure("free dvd "), 0.49);
		spamProbability.put(ObscureService.obscure("free installation "), 0.51);
		hamProbability.put(ObscureService.obscure("free installation "), 0.49);
		spamProbability.put(ObscureService.obscure("free investment "), 0.51);
		hamProbability.put(ObscureService.obscure("free investment "), 0.49);
		spamProbability.put(ObscureService.obscure("free leads "), 0.51);
		hamProbability.put(ObscureService.obscure("free leads "), 0.49);
		spamProbability.put(ObscureService.obscure("free membership "), 0.51);
		hamProbability.put(ObscureService.obscure("free membership "), 0.49);
		spamProbability.put(ObscureService.obscure("free money "), 0.51);
		hamProbability.put(ObscureService.obscure("free money "), 0.49);
		spamProbability.put(ObscureService.obscure("free offer "), 0.51);
		hamProbability.put(ObscureService.obscure("free offer "), 0.49);
		spamProbability.put(ObscureService.obscure("free preview "), 0.51);
		hamProbability.put(ObscureService.obscure("free preview "), 0.49);
		spamProbability.put(ObscureService.obscure("free priority mail "), 0.51);
		hamProbability.put(ObscureService.obscure("free priority mail "), 0.49);
		spamProbability.put(ObscureService.obscure("free quote "), 0.51);
		hamProbability.put(ObscureService.obscure("free quote "), 0.49);
		spamProbability.put(ObscureService.obscure("free sample "), 0.51);
		hamProbability.put(ObscureService.obscure("free sample "), 0.49);
		spamProbability.put(ObscureService.obscure("free trial "), 0.51);
		hamProbability.put(ObscureService.obscure("free trial "), 0.49);
		spamProbability.put(ObscureService.obscure("free website "), 0.51);
		hamProbability.put(ObscureService.obscure("free website "), 0.49);
		spamProbability.put(ObscureService.obscure("amazing "), 0.51);
		hamProbability.put(ObscureService.obscure("amazing "), 0.49);
		spamProbability.put(ObscureService.obscure("congratulations "), 0.51);
		hamProbability.put(ObscureService.obscure("congratulations "), 0.49);
		spamProbability.put(ObscureService.obscure("drastically reduced "), 0.51);
		hamProbability.put(ObscureService.obscure("drastically reduced "), 0.49);
		spamProbability.put(ObscureService.obscure("fantastic deal "), 0.51);
		hamProbability.put(ObscureService.obscure("fantastic deal "), 0.49);
		spamProbability.put(ObscureService.obscure("for free "), 0.51);
		hamProbability.put(ObscureService.obscure("for free "), 0.49);
		spamProbability.put(ObscureService.obscure("outstanding values "), 0.51);
		hamProbability.put(ObscureService.obscure("outstanding values "), 0.49);
		spamProbability.put(ObscureService.obscure("satisfaction guaranteed "), 0.51);
		hamProbability.put(ObscureService.obscure("satisfaction guaranteed "), 0.49);
		spamProbability.put(ObscureService.obscure("apply online "), 0.51);
		hamProbability.put(ObscureService.obscure("apply online "), 0.49);
		spamProbability.put(ObscureService.obscure("call free "), 0.51);
		hamProbability.put(ObscureService.obscure("call free "), 0.49);
		spamProbability.put(ObscureService.obscure("call now "), 0.51);
		hamProbability.put(ObscureService.obscure("call now "), 0.49);
		spamProbability.put(ObscureService.obscure("can't live without"), 0.51);
		hamProbability.put(ObscureService.obscure("can't live without"), 0.49);
		spamProbability.put(ObscureService.obscure("don't delete 	"), 0.51);
		hamProbability.put(ObscureService.obscure("don't delete 	"), 0.49);
		spamProbability.put(ObscureService.obscure("don't hesitate"), 0.51);
		hamProbability.put(ObscureService.obscure("don't hesitate"), 0.49);
		spamProbability.put(ObscureService.obscure("for instant access "), 0.51);
		hamProbability.put(ObscureService.obscure("for instant access "), 0.49);
		spamProbability.put(ObscureService.obscure("get started now "), 0.51);
		hamProbability.put(ObscureService.obscure("get started now "), 0.49);
		spamProbability.put(ObscureService.obscure("great offer "), 0.51);
		hamProbability.put(ObscureService.obscure("great offer "), 0.49);
		spamProbability.put(ObscureService.obscure("new customers only "), 0.51);
		hamProbability.put(ObscureService.obscure("new customers only "), 0.49);
		spamProbability.put(ObscureService.obscure("offer expires "), 0.51);
		hamProbability.put(ObscureService.obscure("offer expires "), 0.49);
		spamProbability.put(ObscureService.obscure("once in lifetime "), 0.51);
		hamProbability.put(ObscureService.obscure("once in lifetime "), 0.49);
		spamProbability.put(ObscureService.obscure("please read "), 0.51);
		hamProbability.put(ObscureService.obscure("please read "), 0.49);
		spamProbability.put(ObscureService.obscure("special promotion "), 0.51);
		hamProbability.put(ObscureService.obscure("special promotion "), 0.49);
		spamProbability.put(ObscureService.obscure("while supplies last "), 0.51);
		hamProbability.put(ObscureService.obscure("while supplies last "), 0.49);
		spamProbability.put(ObscureService.obscure("brand new pager "), 0.51);
		hamProbability.put(ObscureService.obscure("brand new pager "), 0.49);
		spamProbability.put(ObscureService.obscure("cable converter "), 0.51);
		hamProbability.put(ObscureService.obscure("cable converter "), 0.49);
		spamProbability.put(ObscureService.obscure("casino "), 0.51);
		hamProbability.put(ObscureService.obscure("casino "), 0.49);
		spamProbability.put(ObscureService.obscure("copy dvds "), 0.51);
		hamProbability.put(ObscureService.obscure("copy dvds "), 0.49);
		spamProbability.put(ObscureService.obscure("laser printer "), 0.51);
		hamProbability.put(ObscureService.obscure("laser printer "), 0.49);
		spamProbability.put(ObscureService.obscure("luxury car "), 0.51);
		hamProbability.put(ObscureService.obscure("luxury car "), 0.49);
		spamProbability.put(ObscureService.obscure("new domain extensions "), 0.51);
		hamProbability.put(ObscureService.obscure("new domain extensions "), 0.49);
	}

	enum Status
	{
		SPAM, HAM,
	}

	enum Feel
	{
		WORD1(ObscureService.obscure("this isn't a scam")), WORD2(ObscureService.obscure("earn per week")),
		WORD3(ObscureService.obscure("mail in order form")), WORD4(ObscureService.obscure("reverses")),
		WORD5(ObscureService.obscure("#1")), WORD6(ObscureService.obscure("easy terms")),
		WORD7(ObscureService.obscure("maintained")), WORD8(ObscureService.obscure("reverses aging")),
		WORD9(ObscureService.obscure("$$$")), WORD10(ObscureService.obscure("eliminate bad credit")),
		WORD11(ObscureService.obscure("make $")), WORD12(ObscureService.obscure("risk free")),
		WORD13(ObscureService.obscure("hidden assets")), WORD14(ObscureService.obscure("eliminate debt")),
		WORD15(ObscureService.obscure("make money")), WORD16(ObscureService.obscure("rolex")),
		WORD17(ObscureService.obscure("100% free")), WORD18(ObscureService.obscure("email harvest")),
		WORD19(ObscureService.obscure("marketing")), WORD20(ObscureService.obscure("round the world")),
		WORD21(ObscureService.obscure("100% satisfied")), WORD22(ObscureService.obscure("email marketing")),
		WORD23(ObscureService.obscure("marketing solutions")), WORD24(ObscureService.obscure("s 1618")),
		WORD25(ObscureService.obscure("4u")), WORD26(ObscureService.obscure("expect to earn")),
		WORD27(ObscureService.obscure("mass email")), WORD28(ObscureService.obscure("safeguard notice")),
		WORD29(ObscureService.obscure("50% off")), WORD30(ObscureService.obscure("explode your business")),
		WORD31(ObscureService.obscure("medicine")), WORD32(ObscureService.obscure("sale")),
		WORD33(ObscureService.obscure("accept credit cards")), WORD34(ObscureService.obscure("extra income")),
		WORD35(ObscureService.obscure("medium")), WORD36(ObscureService.obscure("sales")),
		WORD37(ObscureService.obscure("acceptance")), WORD38(ObscureService.obscure("f r e e")),
		WORD39(ObscureService.obscure("meet singles")), WORD40(ObscureService.obscure("sample")),
		WORD41(ObscureService.obscure("access")), WORD42(ObscureService.obscure("fantastic deal")),
		WORD43(ObscureService.obscure("member")), WORD44(ObscureService.obscure("satisfaction")),
		WORD45(ObscureService.obscure("accordingly")), WORD46(ObscureService.obscure("fast cash")),
		WORD47(ObscureService.obscure("member stuff")), WORD48(ObscureService.obscure("satisfaction guaranteed")),
		WORD49(ObscureService.obscure("act now")), WORD50(ObscureService.obscure("fast viagra delivery")),
		WORD51(ObscureService.obscure("message contains")), WORD52(ObscureService.obscure("save $")),
		WORD53(ObscureService.obscure("act now!")), WORD54(ObscureService.obscure("financial freedom")),
		WORD55(ObscureService.obscure("message contains disclaimer")), WORD56(ObscureService.obscure("save big money")),
		WORD57(ObscureService.obscure("act now! don’t hesitate!")),
		WORD58(ObscureService.obscure("financially independent")), WORD59(ObscureService.obscure("million")),
		WORD60(ObscureService.obscure("save up to")), WORD61(ObscureService.obscure("ad")),
		WORD62(ObscureService.obscure("for free")), WORD63(ObscureService.obscure("million dollars")),
		WORD64(ObscureService.obscure("score with babes")), WORD65(ObscureService.obscure("additional income")),
		WORD66(ObscureService.obscure("for instant access")), WORD67(ObscureService.obscure("miracle")),
		WORD68(ObscureService.obscure("search engine listings")), WORD69(ObscureService.obscure("addresses on cd")),
		WORD70(ObscureService.obscure("for just $ (some amount)")), WORD71(ObscureService.obscure("mlm")),
		WORD72(ObscureService.obscure("search engines")), WORD73(ObscureService.obscure("affordable")),
		WORD74(ObscureService.obscure("for just $xxx")), WORD75(ObscureService.obscure("money")),
		WORD76(ObscureService.obscure("section 301")), WORD77(ObscureService.obscure("all natural")),
		WORD78(ObscureService.obscure("for only")), WORD79(ObscureService.obscure("money back")),
		WORD80(ObscureService.obscure("see for yourself")), WORD81(ObscureService.obscure("all new")),
		WORD82(ObscureService.obscure("for you")), WORD83(ObscureService.obscure("money making")),
		WORD84(ObscureService.obscure("sent in compliance")), WORD85(ObscureService.obscure("amazing")),
		WORD86(ObscureService.obscure("form")), WORD87(ObscureService.obscure("month trial offer")),
		WORD88(ObscureService.obscure("serious cash")), WORD89(ObscureService.obscure("amazing stuff")),
		WORD90(ObscureService.obscure("free")), WORD91(ObscureService.obscure("more internet traffic")),
		WORD92(ObscureService.obscure("serious only")), WORD93(ObscureService.obscure("apply now")),
		WORD94(ObscureService.obscure("free access")), WORD95(ObscureService.obscure("mortgage")),
		WORD96(ObscureService.obscure("shopper")), WORD97(ObscureService.obscure("apply online")),
		WORD98(ObscureService.obscure("free cell phone")), WORD99(ObscureService.obscure("mortgage rates")),
		WORD100(ObscureService.obscure("shopping spree")), WORD101(ObscureService.obscure("as seen on")),
		WORD102(ObscureService.obscure("free consultation")), WORD103(ObscureService.obscure("multi level marketing")),
		WORD104(ObscureService.obscure("sign up free today")), WORD105(ObscureService.obscure("auto email removal")),
		WORD106(ObscureService.obscure("multi-level marketing")),
		WORD107(ObscureService.obscure("social security number")), WORD108(ObscureService.obscure("avoid")),
		WORD109(ObscureService.obscure("free dvd")), WORD110(ObscureService.obscure("name brand")),
		WORD111(ObscureService.obscure("solution")), WORD112(ObscureService.obscure("avoid bankruptcy")),
		WORD113(ObscureService.obscure("free gift")), WORD114(ObscureService.obscure("never")),
		WORD115(ObscureService.obscure("special promotion")), WORD116(ObscureService.obscure("bargain")),
		WORD117(ObscureService.obscure("free grant money")), WORD118(ObscureService.obscure("new customers only")),
		WORD119(ObscureService.obscure("stainless steel")), WORD120(ObscureService.obscure("be amazed")),
		WORD121(ObscureService.obscure("free hosting")), WORD122(ObscureService.obscure("new domain extensions")),
		WORD123(ObscureService.obscure("stock alert")), WORD124(ObscureService.obscure("be your own boss")),
		WORD125(ObscureService.obscure("free info")), WORD126(ObscureService.obscure("nigerian")),
		WORD127(ObscureService.obscure("stock disclaimer statement")),
		WORD128(ObscureService.obscure("being a member")), WORD129(ObscureService.obscure("free installation")),
		WORD130(ObscureService.obscure("no age restrictions")), WORD131(ObscureService.obscure("stock pick")),
		WORD132(ObscureService.obscure("beneficiary")), WORD133(ObscureService.obscure("free instant")),
		WORD134(ObscureService.obscure("no catch")), WORD135(ObscureService.obscure("stop")),
		WORD136(ObscureService.obscure("best price")), WORD137(ObscureService.obscure("free investment")),
		WORD138(ObscureService.obscure("no claim forms")), WORD139(ObscureService.obscure("stop snoring")),
		WORD140(ObscureService.obscure("beverage")), WORD141(ObscureService.obscure("free leads")),
		WORD142(ObscureService.obscure("no cost")), WORD143(ObscureService.obscure("strong buy")),
		WORD144(ObscureService.obscure("big bucks")), WORD145(ObscureService.obscure("free membership")),
		WORD146(ObscureService.obscure("no credit check")), WORD147(ObscureService.obscure("stuff on sale")),
		WORD148(ObscureService.obscure("bill 1618")), WORD149(ObscureService.obscure("free money")),
		WORD150(ObscureService.obscure("no disappointment")), WORD151(ObscureService.obscure("subject to cash")),
		WORD152(ObscureService.obscure("billing address")), WORD153(ObscureService.obscure("free offer")),
		WORD154(ObscureService.obscure("no experience")), WORD155(ObscureService.obscure("subject to credit")),
		WORD156(ObscureService.obscure("billion")), WORD157(ObscureService.obscure("free preview")),
		WORD158(ObscureService.obscure("no fees")), WORD159(ObscureService.obscure("subscribe")),
		WORD160(ObscureService.obscure("billion dollars")), WORD161(ObscureService.obscure("free priority mail")),
		WORD162(ObscureService.obscure("no gimmick")), WORD163(ObscureService.obscure("success")),
		WORD164(ObscureService.obscure("bonus")), WORD165(ObscureService.obscure("free quote")),
		WORD166(ObscureService.obscure("no hidden")), WORD167(ObscureService.obscure("supplies are limited")),
		WORD168(ObscureService.obscure("brand new pager")), WORD169(ObscureService.obscure("free sample")),
		WORD170(ObscureService.obscure("no inventory")), WORD171(ObscureService.obscure("take action now")),
		WORD172(ObscureService.obscure("bulk email")), WORD173(ObscureService.obscure("free trial")),
		WORD174(ObscureService.obscure("no investment")), WORD175(ObscureService.obscure("talks about hidden charges")),
		WORD176(ObscureService.obscure("buy")), WORD177(ObscureService.obscure("free website")),
		WORD178(ObscureService.obscure("no medical exams")), WORD179(ObscureService.obscure("talks about prizes")),
		WORD180(ObscureService.obscure("buy direct")), WORD181(ObscureService.obscure("freedom")),
		WORD182(ObscureService.obscure("no middleman")), WORD183(ObscureService.obscure("teen")),
		WORD184(ObscureService.obscure("buying judgements")), WORD185(ObscureService.obscure("friend")),
		WORD186(ObscureService.obscure("no obligation")), WORD187(ObscureService.obscure("tells you it’s an ad")),
		WORD188(ObscureService.obscure("buying judgments")), WORD189(ObscureService.obscure("full refund")),
		WORD190(ObscureService.obscure("no purchase necessary")),
		WORD191(ObscureService.obscure("terms and conditions")), WORD192(ObscureService.obscure("cable converter")),
		WORD193(ObscureService.obscure("get")), WORD194(ObscureService.obscure("no questions asked")),
		WORD195(ObscureService.obscure("the best rates")), WORD196(ObscureService.obscure("call")),
		WORD197(ObscureService.obscure("get it now")), WORD198(ObscureService.obscure("no selling")),
		WORD199(ObscureService.obscure("the following form")), WORD200(ObscureService.obscure("call free")),
		WORD201(ObscureService.obscure("get out of debt")), WORD202(ObscureService.obscure("no strings attached")),
		WORD203(ObscureService.obscure("they keep your money ?no refund")), WORD204(ObscureService.obscure("call now")),
		WORD205(ObscureService.obscure("get paid")), WORD206(ObscureService.obscure("no-obligation")),
		WORD207(ObscureService.obscure("they’re just giving it away")),
		WORD208(ObscureService.obscure("calling creditors")), WORD209(ObscureService.obscure("get started now")),
		WORD210(ObscureService.obscure("not intended")), WORD211(ObscureService.obscure("this isn’t junk")),
		WORD212(ObscureService.obscure("cancel at any time")), WORD213(ObscureService.obscure("gift certificate")),
		WORD214(ObscureService.obscure("notspam")), WORD215(ObscureService.obscure("this isn’t spam")),
		WORD216(ObscureService.obscure("cannot be combined with any other offer")),
		WORD217(ObscureService.obscure("give it away")), WORD218(ObscureService.obscure("now")),
		WORD219(ObscureService.obscure("this won’t last")), WORD220(ObscureService.obscure("can’t live without")),
		WORD221(ObscureService.obscure("giving away")), WORD222(ObscureService.obscure("now only")),
		WORD223(ObscureService.obscure("thousands")), WORD224(ObscureService.obscure("cards accepted")),
		WORD225(ObscureService.obscure("great offer")), WORD226(ObscureService.obscure("obligation")),
		WORD227(ObscureService.obscure("time limited")), WORD228(ObscureService.obscure("cash")),
		WORD229(ObscureService.obscure("guarantee")), WORD230(ObscureService.obscure("off shore")),
		WORD231(ObscureService.obscure("trial")), WORD232(ObscureService.obscure("cash bonus")),
		WORD233(ObscureService.obscure("guaranteed")), WORD234(ObscureService.obscure("offer")),
		WORD235(ObscureService.obscure("undisclosed recipient")), WORD236(ObscureService.obscure("cashcashcash")),
		WORD237(ObscureService.obscure("have you been turned down?")), WORD238(ObscureService.obscure("offer expires")),
		WORD239(ObscureService.obscure("university diplomas")), WORD240(ObscureService.obscure("casino")),
		WORD241(ObscureService.obscure("hello")), WORD242(ObscureService.obscure("once in lifetime")),
		WORD243(ObscureService.obscure("unlimited")), WORD244(ObscureService.obscure("celebrity")),
		WORD245(ObscureService.obscure("here")), WORD246(ObscureService.obscure("one hundred percent free")),
		WORD247(ObscureService.obscure("unsecured credit")), WORD248(ObscureService.obscure("cell phone cancer scam")),
		WORD249(ObscureService.obscure("hidden")), WORD250(ObscureService.obscure("one hundred percent guaranteed")),
		WORD251(ObscureService.obscure("unsecured credit/debt")),
		WORD252(ObscureService.obscure("cents on the dollar")), WORD253(ObscureService.obscure("hidden assets")),
		WORD254(ObscureService.obscure("one time")), WORD255(ObscureService.obscure("unsecured debt")),
		WORD256(ObscureService.obscure("certified")), WORD257(ObscureService.obscure("hidden charges")),
		WORD258(ObscureService.obscure("one time mailing")), WORD259(ObscureService.obscure("unsolicited")),
		WORD260(ObscureService.obscure("chance")), WORD261(ObscureService.obscure("home")),
		WORD262(ObscureService.obscure("online biz opportunity")), WORD263(ObscureService.obscure("unsubscribe")),
		WORD264(ObscureService.obscure("cheap")), WORD265(ObscureService.obscure("home based")),
		WORD266(ObscureService.obscure("online degree")), WORD267(ObscureService.obscure("urgent")),
		WORD268(ObscureService.obscure("check")), WORD269(ObscureService.obscure("home employment")),
		WORD270(ObscureService.obscure("online marketing")), WORD271(ObscureService.obscure("us dollars")),
		WORD272(ObscureService.obscure("check or money order")), WORD273(ObscureService.obscure("homebased business")),
		WORD274(ObscureService.obscure("online pharmacy")), WORD275(ObscureService.obscure("vacation")),
		WORD276(ObscureService.obscure("claims")), WORD277(ObscureService.obscure("human growth hormone")),
		WORD278(ObscureService.obscure("only")), WORD279(ObscureService.obscure("vacation offers")),
		WORD280(ObscureService.obscure("claims not to be selling anything")),
		WORD281(ObscureService.obscure("if only it were that easy")), WORD282(ObscureService.obscure("only $")),
		WORD283(ObscureService.obscure("valium")),
		WORD284(ObscureService.obscure("claims to be in accordance with some spam law")),
		WORD285(ObscureService.obscure("important information regarding")), WORD286(ObscureService.obscure("open")),
		WORD287(ObscureService.obscure("viagra")), WORD288(ObscureService.obscure("claims to be legal")),
		WORD289(ObscureService.obscure("in accordance with laws")), WORD290(ObscureService.obscure("opportunity")),
		WORD291(ObscureService.obscure("viagra and other drugs")), WORD292(ObscureService.obscure("clearance")),
		WORD293(ObscureService.obscure("income")), WORD294(ObscureService.obscure("opt in")),
		WORD295(ObscureService.obscure("vicodin")), WORD296(ObscureService.obscure("click")),
		WORD297(ObscureService.obscure("income from home")), WORD298(ObscureService.obscure("order")),
		WORD299(ObscureService.obscure("visit our website")), WORD300(ObscureService.obscure("click below")),
		WORD301(ObscureService.obscure("increase sales")), WORD302(ObscureService.obscure("order now")),
		WORD303(ObscureService.obscure("wants credit card")), WORD304(ObscureService.obscure("click here")),
		WORD305(ObscureService.obscure("increase traffic")), WORD306(ObscureService.obscure("order shipped by")),
		WORD307(ObscureService.obscure("warranty")), WORD308(ObscureService.obscure("click to remove")),
		WORD309(ObscureService.obscure("increase your sales")), WORD310(ObscureService.obscure("order status")),
		WORD311(ObscureService.obscure("we hate spam")), WORD312(ObscureService.obscure("collect")),
		WORD313(ObscureService.obscure("incredible deal")), WORD314(ObscureService.obscure("order today")),
		WORD315(ObscureService.obscure("we honor all")), WORD316(ObscureService.obscure("collect child support")),
		WORD317(ObscureService.obscure("info you requested")), WORD318(ObscureService.obscure("orders shipped by")),
		WORD319(ObscureService.obscure("web traffic")), WORD320(ObscureService.obscure("compare")),
		WORD321(ObscureService.obscure("information you requested")),
		WORD322(ObscureService.obscure("outstanding values")), WORD323(ObscureService.obscure("weekend getaway")),
		WORD324(ObscureService.obscure("compare rates")), WORD325(ObscureService.obscure("instant")),
		WORD326(ObscureService.obscure("passwords")), WORD327(ObscureService.obscure("weight loss")),
		WORD328(ObscureService.obscure("compete for your business")), WORD329(ObscureService.obscure("insurance")),
		WORD330(ObscureService.obscure("pennies a day")), WORD331(ObscureService.obscure("what are you waiting for?")),
		WORD332(ObscureService.obscure("confidentially on all orders")),
		WORD333(ObscureService.obscure("internet market")), WORD334(ObscureService.obscure("per day")),
		WORD335(ObscureService.obscure("while supplies last")), WORD336(ObscureService.obscure("congratulations")),
		WORD337(ObscureService.obscure("internet marketing")), WORD338(ObscureService.obscure("per week")),
		WORD339(ObscureService.obscure("while you sleep")),
		WORD340(ObscureService.obscure("consolidate debt and credit")), WORD341(ObscureService.obscure("investment")),
		WORD342(ObscureService.obscure("performance")), WORD343(ObscureService.obscure("who really wins?")),
		WORD344(ObscureService.obscure("consolidate your debt")),
		WORD345(ObscureService.obscure("investment decision")), WORD346(ObscureService.obscure("phone")),
		WORD347(ObscureService.obscure("why pay more?")), WORD348(ObscureService.obscure("copy accurately")),
		WORD349(ObscureService.obscure("it’s effective")), WORD350(ObscureService.obscure("please read")),
		WORD351(ObscureService.obscure("wife")), WORD352(ObscureService.obscure("copy dvds")),
		WORD353(ObscureService.obscure("potential earnings")),
		WORD354(ObscureService.obscure("will not believe your eyes")), WORD355(ObscureService.obscure("cost")),
		WORD356(ObscureService.obscure("join millions")), WORD357(ObscureService.obscure("pre-approved")),
		WORD358(ObscureService.obscure("win")), WORD359(ObscureService.obscure("costs")),
		WORD360(ObscureService.obscure("join millions of americans")), WORD361(ObscureService.obscure("price")),
		WORD362(ObscureService.obscure("winner")), WORD363(ObscureService.obscure("credit")),
		WORD364(ObscureService.obscure("laser printer")), WORD365(ObscureService.obscure("prices")),
		WORD366(ObscureService.obscure("credit bureaus")), WORD367(ObscureService.obscure("leave")),
		WORD368(ObscureService.obscure("print form signature")), WORD369(ObscureService.obscure("winning")),
		WORD370(ObscureService.obscure("credit card offers")), WORD371(ObscureService.obscure("legal")),
		WORD372(ObscureService.obscure("print out and fax")), WORD373(ObscureService.obscure("won")),
		WORD374(ObscureService.obscure("cures baldness")), WORD375(ObscureService.obscure("life")),
		WORD376(ObscureService.obscure("priority mail")), WORD377(ObscureService.obscure("work at home")),
		WORD378(ObscureService.obscure("deal")), WORD379(ObscureService.obscure("life insurance")),
		WORD380(ObscureService.obscure("prize")), WORD381(ObscureService.obscure("work from home")),
		WORD382(ObscureService.obscure("dear [email/friend/somebody]")), WORD383(ObscureService.obscure("lifetime")),
		WORD384(ObscureService.obscure("prizes")), WORD385(ObscureService.obscure("xanax")),
		WORD386(ObscureService.obscure("diagnostics")), WORD387(ObscureService.obscure("limited time")),
		WORD388(ObscureService.obscure("problem")), WORD389(ObscureService.obscure("you are a winner!")),
		WORD390(ObscureService.obscure("dig up dirt on friends")),
		WORD391(ObscureService.obscure("limited time offer")), WORD392(ObscureService.obscure("produced and sent out")),
		WORD393(ObscureService.obscure("you have been selected")), WORD394(ObscureService.obscure("direct email")),
		WORD395(ObscureService.obscure("limited time only")), WORD396(ObscureService.obscure("profits")),
		WORD397(ObscureService.obscure("you’re a winner!")), WORD398(ObscureService.obscure("direct marketing")),
		WORD399(ObscureService.obscure("loans")), WORD400(ObscureService.obscure("promise you")),
		WORD401(ObscureService.obscure("your income")), WORD402(ObscureService.obscure("discount")),
		WORD403(ObscureService.obscure("long distance phone offer")), WORD404(ObscureService.obscure("pure profits")),
		WORD405(ObscureService.obscure("do it today")), WORD406(ObscureService.obscure("lose")),
		WORD407(ObscureService.obscure("quote")), WORD408(ObscureService.obscure("don’t delete")),
		WORD409(ObscureService.obscure("lose weight")), WORD410(ObscureService.obscure("quotes")),
		WORD411(ObscureService.obscure("don’t hesitate")), WORD412(ObscureService.obscure("lose weight spam")),
		WORD413(ObscureService.obscure("real thing")), WORD414(ObscureService.obscure("dormant")),
		WORD415(ObscureService.obscure("lower interest rate")), WORD416(ObscureService.obscure("refinance")),
		WORD417(ObscureService.obscure("double your")), WORD418(ObscureService.obscure("lower interest rates")),
		WORD419(ObscureService.obscure("refinance home")), WORD420(ObscureService.obscure("double your income")),
		WORD421(ObscureService.obscure("lower monthly payment")), WORD422(ObscureService.obscure("refinanced home")),
		WORD423(ObscureService.obscure("drastically reduced")),
		WORD424(ObscureService.obscure("lower your mortgage rate")),
		WORD425(ObscureService.obscure("removal instructions")), WORD426(ObscureService.obscure("earn")),
		WORD427(ObscureService.obscure("lowest insurance rates")), WORD428(ObscureService.obscure("remove")),
		WORD429(ObscureService.obscure("earn $")), WORD430(ObscureService.obscure("lowest price")),
		WORD431(ObscureService.obscure("removes wrinkles")), WORD432(ObscureService.obscure("earn extra cash")),
		WORD433(ObscureService.obscure("luxury car")), WORD434(ObscureService.obscure("reserves the right")),
		WORD435(ObscureService.obscure("100%")), WORD436(ObscureService.obscure("action")),
		WORD437(ObscureService.obscure("amazed")), WORD438(ObscureService.obscure("billing")),
		WORD439(ObscureService.obscure("boss")), WORD440(ObscureService.obscure("cancel")),
		WORD441(ObscureService.obscure("cures")), WORD442(ObscureService.obscure("debt")),
		WORD443(ObscureService.obscure("double your cash")), WORD444(ObscureService.obscure("exclusive deal")),
		WORD445(ObscureService.obscure("expire")), WORD446(ObscureService.obscure("extra")),
		WORD447(ObscureService.obscure("extra cash")), WORD448(ObscureService.obscure("fantastic")),
		WORD449(ObscureService.obscure("great")), WORD450(ObscureService.obscure("home based business")),
		WORD451(ObscureService.obscure("junk")), WORD452(ObscureService.obscure("limited")),
		WORD453(ObscureService.obscure("loan")), WORD454(ObscureService.obscure("luxury")),
		WORD455(ObscureService.obscure("no hidden costs")), WORD456(ObscureService.obscure("no interests")),
		WORD457(ObscureService.obscure("not junk")), WORD458(ObscureService.obscure("not spam")),
		WORD459(ObscureService.obscure("offshore")), WORD460(ObscureService.obscure("presently")),
		WORD461(ObscureService.obscure("promise")), WORD462(ObscureService.obscure("purchase")),
		WORD463(ObscureService.obscure("rates")), WORD464(ObscureService.obscure("refund")),
		WORD465(ObscureService.obscure("removal")), WORD466(ObscureService.obscure("removal instructions")),
		WORD467(ObscureService.obscure("remove")), WORD468(ObscureService.obscure("removes wrinkles")),
		WORD469(ObscureService.obscure("request")), WORD470(ObscureService.obscure("requires initial investment")),
		WORD471(ObscureService.obscure("score")), WORD472(ObscureService.obscure("serious")),
		WORD473(ObscureService.obscure("spam")), WORD474(ObscureService.obscure("supplies")),
		WORD475(ObscureService.obscure("take action")), WORD476(ObscureService.obscure("terms")),
		WORD477(ObscureService.obscure("they keep your money ?no refund!")), WORD478(ObscureService.obscure("traffic")),
		WORD479(ObscureService.obscure("weight")), WORD480(ObscureService.obscure("what’s keeping you?")),
		WORD481(ObscureService.obscure(" as seen on")), WORD482(ObscureService.obscure("dig up dirt on friends")),
		WORD483(ObscureService.obscure("meet singles")), WORD484(ObscureService.obscure(" additional income")),
		WORD485(ObscureService.obscure("earn per week")), WORD486(ObscureService.obscure("expect to earn")),
		WORD487(ObscureService.obscure("opportunity")), WORD488(ObscureService.obscure("potential earnings")),
		WORD489(ObscureService.obscure("while you sleep")), WORD490(ObscureService.obscure("work at home")),
		WORD491(ObscureService.obscure("big bucks")), WORD492(ObscureService.obscure("cash bonus")),
		WORD493(ObscureService.obscure("cents on the dollar")), WORD494(ObscureService.obscure("credit bureaus")),
		WORD495(ObscureService.obscure("easy terms")), WORD496(ObscureService.obscure("hidden assets")),
		WORD497(ObscureService.obscure("insurance")), WORD498(ObscureService.obscure("lowest price")),
		WORD499(ObscureService.obscure("mortgage rates")), WORD500(ObscureService.obscure("no fees")),
		WORD501(ObscureService.obscure("one hundred percent free")), WORD502(ObscureService.obscure("only $")),
		WORD503(ObscureService.obscure("pennies a day")), WORD504(ObscureService.obscure("profits")),
		WORD505(ObscureService.obscure("pure profit")),
		WORD506(ObscureService.obscure("they keep your money -- no refund!")),
		WORD507(ObscureService.obscure("why pay more?")), WORD508(ObscureService.obscure("check or money order")),
		WORD509(ObscureService.obscure("credit card offers")), WORD510(ObscureService.obscure("full refund")),
		WORD511(ObscureService.obscure("investment decision")), WORD512(ObscureService.obscure("no credit check")),
		WORD513(ObscureService.obscure("stock alert")), WORD514(ObscureService.obscure("stock disclaimer statement")),
		WORD515(ObscureService.obscure("stock pick")), WORD516(ObscureService.obscure("eliminate bad credit")),
		WORD517(ObscureService.obscure("get paid")), WORD518(ObscureService.obscure("lower monthly payment")),
		WORD519(ObscureService.obscure("refinance home")), WORD520(ObscureService.obscure("social security number")),
		WORD521(ObscureService.obscure("dear [email/friend/somebody]")),
		WORD522(ObscureService.obscure("auto email removal")), WORD523(ObscureService.obscure("click to remove")),
		WORD524(ObscureService.obscure("direct email")), WORD525(ObscureService.obscure("direct marketing")),
		WORD526(ObscureService.obscure("email harvest")), WORD527(ObscureService.obscure("email marketing")),
		WORD528(ObscureService.obscure("increase sales")), WORD529(ObscureService.obscure("increase traffic")),
		WORD530(ObscureService.obscure("marketing solutions")), WORD531(ObscureService.obscure("mass email")),
		WORD532(ObscureService.obscure("one time mailing")), WORD533(ObscureService.obscure("this isn't junk")),
		WORD534(ObscureService.obscure("this isn't spam")), WORD535(ObscureService.obscure("we hate spam")),
		WORD536(ObscureService.obscure("will not believe your eyes")),
		WORD537(ObscureService.obscure("cures baldness")), WORD538(ObscureService.obscure("lose weight spam")),
		WORD539(ObscureService.obscure("online pharmacy")), WORD540(ObscureService.obscure("stop snoring")),
		WORD541(ObscureService.obscure("billion dollars")),
		WORD542(ObscureService.obscure("join millions of americans")),
		WORD543(ObscureService.obscure("one hundred percent guaranteed")),
		WORD544(ObscureService.obscure("billing address")),
		WORD545(ObscureService.obscure("cannot be combined with any other offer")),
		WORD546(ObscureService.obscure("confidentially on all orders")),
		WORD547(ObscureService.obscure("financial freedom")), WORD548(ObscureService.obscure("guarantee")),
		WORD549(ObscureService.obscure("have you been turned down?")),
		WORD550(ObscureService.obscure("mail in order form")), WORD551(ObscureService.obscure("name brand")),
		WORD552(ObscureService.obscure("nigerian")), WORD553(ObscureService.obscure("no age restrictions")),
		WORD554(ObscureService.obscure("no catch")), WORD555(ObscureService.obscure("no claim forms")),
		WORD556(ObscureService.obscure("no disappointment")), WORD557(ObscureService.obscure("no experience")),
		WORD558(ObscureService.obscure("no gimmick")), WORD559(ObscureService.obscure("no inventory")),
		WORD560(ObscureService.obscure("not intended")), WORD561(ObscureService.obscure("produced and sent out")),
		WORD562(ObscureService.obscure("shopping spree")), WORD563(ObscureService.obscure("we honor all")),
		WORD564(ObscureService.obscure("weekend getaway")),
		WORD565(ObscureService.obscure("what are you waiting for?")),
		WORD566(ObscureService.obscure("who really wins?")), WORD567(ObscureService.obscure("winner")),
		WORD568(ObscureService.obscure("winning")), WORD569(ObscureService.obscure("you have been selected")),
		WORD571(ObscureService.obscure("cancel at any time")), WORD572(ObscureService.obscure("copy accurately")),
		WORD573(ObscureService.obscure("print out and fax")), WORD574(ObscureService.obscure("sign up free today")),
		WORD575(ObscureService.obscure("free access")), WORD576(ObscureService.obscure("free cell phone")),
		WORD577(ObscureService.obscure("free consultation")), WORD578(ObscureService.obscure("free dvd")),
		WORD579(ObscureService.obscure("free installation")), WORD580(ObscureService.obscure("free investment")),
		WORD581(ObscureService.obscure("free leads")), WORD582(ObscureService.obscure("free membership")),
		WORD583(ObscureService.obscure("free money")), WORD584(ObscureService.obscure("free offer")),
		WORD585(ObscureService.obscure("free preview")), WORD586(ObscureService.obscure("free priority mail")),
		WORD587(ObscureService.obscure("free quote")), WORD588(ObscureService.obscure("free sample")),
		WORD589(ObscureService.obscure("free trial")), WORD590(ObscureService.obscure("free website")),
		WORD591(ObscureService.obscure("amazing")), WORD592(ObscureService.obscure("congratulations")),
		WORD593(ObscureService.obscure("drastically reduced")), WORD594(ObscureService.obscure("fantastic deal")),
		WORD595(ObscureService.obscure("for free")), WORD596(ObscureService.obscure("outstanding values")),
		WORD597(ObscureService.obscure("satisfaction guaranteed")), WORD598(ObscureService.obscure("apply online")),
		WORD599(ObscureService.obscure("call free")), WORD600(ObscureService.obscure("call now")),
		WORD601(ObscureService.obscure("can't live without")), WORD602(ObscureService.obscure("don't delete")),
		WORD603(ObscureService.obscure("don't hesitate")), WORD604(ObscureService.obscure("for instant access")),
		WORD605(ObscureService.obscure("get started now")), WORD606(ObscureService.obscure("great offer")),
		WORD607(ObscureService.obscure("new customers only")), WORD608(ObscureService.obscure("offer expires")),
		WORD609(ObscureService.obscure("once in lifetime")), WORD610(ObscureService.obscure("please read")),
		WORD611(ObscureService.obscure("special promotion")), WORD612(ObscureService.obscure("while supplies last")),
		WORD613(ObscureService.obscure("brand new pager")), WORD614(ObscureService.obscure("cable converter")),
		WORD615(ObscureService.obscure("casino")), WORD616(ObscureService.obscure("copy dvds")),
		WORD617(ObscureService.obscure("laser printer")), WORD618(ObscureService.obscure("luxury car")),
		WORD619(ObscureService.obscure("new domain extensions"));

		String data;

		Feel(String data)
		{
			this.data = data;
		}

		public String getData()
		{
			return this.data;
		}

		// Lookup table
		private static final Map<String, Feel> lookup = new HashMap<>();

		// Populate the lookup table on loading time
		static
		{
			for (Feel env : Feel.values())
			{
				lookup.put(env.getData(), env);
			}
		}

		// This method can be used for reverse lookup purpose
		public static Feel get(String url)
		{
			return lookup.get(url);
		}

	}

	static int[] states = new int[]
	{ SPAM.ordinal(), HAM.ordinal() };
	static double[] start_probability = new double[]
	{ 0.5, 0.5 };
	static double[][] transititon_probability = new double[][]
	{
			{ 0.5, 0.5 },
			{ 0.5, 0.5 }, };

	public String runHMM(File f) throws Exception
	{
		List<Integer> obs = new ArrayList<>();

		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));

		String line;

		while ((line = br.readLine()) != null)
		{
			for (Feel feel : Feel.values())
			{
				if (line.contains(feel.getData()))
				{
					obs.add(feel.ordinal());
				}
			}
		}

		// System.out.println(obs);

		int[] observations = new int[obs.size()];
		for (int i = 0; i < obs.size(); i++)
		{
			if (obs.get(i) != null)
			{
				observations[i] = obs.get(i);
			}
		}
		br.close();

		double[][] emission_probability = new double[2][1000];

		for (int i = 0; i < observations.length; i++)
		{
			emission_probability[0][observations[i]] = spamProbability.get(Feel.values()[observations[i]].getData());
			emission_probability[1][observations[i]] = hamProbability.get(Feel.values()[observations[i]].getData());
		}

		if (obs.size() == 0)
		{
			System.out.println("HAM");
			return Constants.HAM;
		} else
		{

			int[] result = Viterbi.compute(observations, states, start_probability, transititon_probability,
					emission_probability);
			int spamCount = 0;
			int hamCount = 0;
			for (int r : result)
			{
				if (Status.values()[r] == Status.HAM)
					hamCount++;
				else
					spamCount++;

			}
			if ((spamCount >= hamCount) && spamCount > 5)
			{
				System.out.println("SPAM");
				return Constants.SPAM;
			} else
			{
				System.out.println("HAM");
				return Constants.HAM;
			}
		}
	}

	public String runHMM(String contents) throws Exception
	{
		List<Integer> obs = new ArrayList<>();

		String line = contents;
		;

		for (Feel feel : Feel.values())
		{
			if (line.contains(feel.getData()))
			{
				obs.add(feel.ordinal());
			}
		}

		// System.out.println(obs);

		int[] observations = new int[obs.size()];
		for (int i = 0; i < obs.size(); i++)
		{
			if (obs.get(i) != null)
			{
				observations[i] = obs.get(i);
			}
		}

		double[][] emission_probability = new double[2][1000];

		for (int i = 0; i < observations.length; i++)
		{
			emission_probability[0][observations[i]] = spamProbability.get(Feel.values()[observations[i]].getData());
			emission_probability[1][observations[i]] = hamProbability.get(Feel.values()[observations[i]].getData());
		}

		if (obs.size() == 0)
		{
			System.out.println("HAM");
			return Constants.HAM;
		} else
		{

			int[] result = Viterbi.compute(observations, states, start_probability, transititon_probability,
					emission_probability);
			int spamCount = 0;
			int hamCount = 0;
			for (int r : result)
			{
				if (Status.values()[r] == Status.HAM)
					hamCount++;
				else
					spamCount++;

			}
			if ((spamCount >= hamCount) && spamCount > 5)
			{
				System.out.println("SPAM");
				return Constants.SPAM;
			} else
			{
				System.out.println("HAM");
				return Constants.HAM;
			}
		}
	}
}
