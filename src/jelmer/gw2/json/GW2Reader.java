package jelmer.gw2.json;

import java.io.IOException;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GW2Reader extends JsonReader {

	public static JSONObject getItemsFromId(int id) throws JSONException, IOException {
		String url="https://api.guildwars2.com/v2/items/"+Integer.toString(id);
		JSONObject json = readJsonFromUrl(url);
		return json;
	}
	
	public static <T extends Number> JSONArray getItemsFromId(Collection<T> idList) throws JSONException, IOException{

		StringBuilder url =new StringBuilder("https://api.guildwars2.com/v2/items?ids=");
		for(T id:idList) {
			url.append(","+id);
		}
		JSONArray json=readJsonArrayFromUrl(url.toString());
		return json;
		
	}
	
	public static JSONObject getCommerceListingsFromId(int id) throws JSONException,IOException {
		String url = "https://api.guildwars2.com/v2/commerce/listings/"+Integer.toString(id);
		JSONObject json=readJsonFromUrl(url);
		return json;
	}
	
	public static<T extends Number> JSONArray getCommerceListingsFromId(Collection<T> idList) throws JSONException,IOException {
		StringBuilder url = new StringBuilder("https://api.guildwars2.com/v2/commerce/prices?ids=");
		for(T id:idList) {
			url.append(","+id);
		}
		JSONArray json = readJsonArrayFromUrl(url.toString());
		return json;
	}

	public static JSONObject getCommercePricesFromId(int id) throws JSONException,IOException {
		String url = "https://api.guildwars2.com/v2/commerce/prices/"+Integer.toString(id);
		JSONObject json=readJsonFromUrl(url);
		return json;
	}
	
	public static JSONArray getCommerceTransactionsHistoryBuysFromApiKey(String api_key) throws JSONException, IOException{
		String url="https://api.guildwars2.com/v2/commerce/transactions/history/buys?access_token="+api_key;
		JSONArray json=readJsonArrayFromUrl(url);
		return json;
	}
	
	public static JSONArray getCommerceTransactionHistorySellsFromApiKey(String api_key) throws JSONException, IOException {
		String url ="https://api.guildwars2.com/v2/commerce/transactions/history/sells?access_token="+api_key;
		JSONArray json=readJsonArrayFromUrl(url);
		return json;
	}
	
	public static JSONArray getListOfAllItemIds() throws JSONException,IOException {
		String url="https://api.guildwars2.com/v2/items";
		JSONArray json=readJsonArrayFromUrl(url);
		return json;
	}

	public static JSONArray getCommerceTransactionsCurrentBuysFromApiKey(String api_key) throws JSONException, IOException {
		String url ="https://api.guildwars2.com/v2/commerce/transactions/current/buys?access_token="+api_key;
		JSONArray json= readJsonArrayFromUrl(url);
		return json;
	}

	public static JSONArray getCommerceTransactionsCurrentSellsFromApiKey(String api_key) throws JSONException, IOException {
		String url ="https://api.guildwars2.com/v2/commerce/transactions/current/sells?access_token="+api_key;
		JSONArray json= readJsonArrayFromUrl(url);
		return json;
	}

	public static JSONArray getListOfAllTpIds() throws JSONException, IOException {
		String url="https://api.guildwars2.com/v2/commerce/prices";
		JSONArray json=readJsonArrayFromUrl(url);
		return json;
	}

	public static<T extends Number> JSONArray getTpPricesFromID(Collection<T> subList) throws JSONException, IOException {

		StringBuilder url =new StringBuilder("https://api.guildwars2.com/v2/commerce/prices?ids=");
		for(T id:subList) {
			url.append(","+id);
		}
		JSONArray json=readJsonArrayFromUrl(url.toString());
		return json;
	}

}
