package com.iesruizgijon.fleamarket.util.constans;

public class RestConstants {

	public static final String APPLICATION_NAME = "/flea-market";
	public static final String API_VERSION_1 = "/v1";
	public static final String SUCCESS = "Success";


	/*RESOURCE*/

	/*User*/
	public static final String RESOURCE_USER = "/user";

	public static final String RESOURCE_USER_LOGIN = "/user/login";
	public static final String RESOURCE_USER_ID = "/{idUser}";

	public static final String RESOURCE_USER_SHORT_ID = "/short/{idUser}";

	public static final String RESOURCE_USER_MEDIUM_ID = "/medium/{idUser}";
	public static final String RESOURCE_USER_CHECK = "/check-in";

	public static final String RESOURCE_USER_LIST = "/list-users";

	public static final String RESOURCE_USER_LIKE = "/like/id-user/{idUser}/ref-ad/{refAd}";

	public static final String RESOURCE_USER_PASSWORD = "/password";

	public static final String RESOURCE_USER_NICK = "/nick-user/{nickUser}";

	public static final String RESOURCE_USER_EMAIL = "/email-user/{emailUser}";


	/*Ad*/

	public static final String RESOURCE_AD = "/ad";

	public static final String RESOURCE_AD_REF = "/{refAd}";
	public static final String RESOURCE_AD_NEW = "/new-ad";

	public static final String RESOURCE_AD_LIST_ALL = "/list-ad-all";

	public static final String RESOURCE_AD_LIST_CAT = "/list-ad-cat/{idCate}";

	public static final String RESOURCE_AD_LIST_FILTER = "/list-ad-filter";


	public static final String RESOURCE_AD_LIST_SUB_CAT = "/list-ad-sub-cat/{idSubCat}";

	public static final String RESOURCE_AD_LIST_USER = "/list-ad-user/{idUser}";

	public static final String RESOURCE_AD_LIST_LIKE_USER = "/list-ad-like-user/{idUser}";


	/*AC*/
	public static final String RESOURCE_AC = "/ac";

	public static final String RESOURCE_AC_Id = "/ac/{idAC}";

	public static final String RESOURCE_AC_LIST = "/list-all";

	/*Province*/
	public static final String RESOURCE_PROVINCE = "/province";

	public static final String RESOURCE_PROVINCE_ID = "/{idPr}";

	public static final String RESOURCE_PROVINCE_LIST_BY_AC = "/list-by-ac/{idAC}";

	/*Locality*/
	public static final String RESOURCE_LOCALITY = "/locality";

	public static final String RESOURCE_LOCALITY_ID = "/{idLo}";

	public static final String RESOURCE_LOCALITY_LIST_BY_PR = "list-by-pr/{idPr}";

	/*SubcategoryAd*/
	public static final String RESOURCE_SUB_CAT = "/sub-cat";

	public static final String RESOURCE_SUB_CAT_ID = "/{idSubCat}";

	public static final String RESOURCE_SUB_CAT_LIST = "/list-by-cat/{idCat}";

	/*CategoryAd*/
	public static final String RESOURCE_CAT = "/cat";

	public static final String RESOURCE_CAT_ID = "/{idCat}";

	public static final String RESOURCE_CAT_LIST = "/list-all";

	public static final String RESOURCE_CAT_LIST_AND_SUBCAT = "/list-all-and-sub";

	/*PARAMETERS*/

	/*user*/
	public static final String PARAMETER_USER = "User";
	public static final String PARAMETER_USER_ID = "idUser";
	public static final String PARAMETER_USER_NICK = "nickUser";
	public static final String PARAMETER_USER_EMAIL = "emailUser";

	private RestConstants() {throw new IllegalStateException("Utility Class");}



	/*Filter*/

	public static final String PARAMETER_FILTER = "filter";
	public static final String PARAMETER_FILTER_SEARCH = "search";
	public static final String PARAMETER_FILTER_CAT = "category";
	public static final String PARAMETER_FILTER_SUBCAT= "subcategory";
	public static final String PARAMETER_FILTER_AC = "ac";
	public static final String PARAMETER_FILTER_PR = "province";
	public static final String PARAMETER_FILTER_LO = "locality";
	public static final String PARAMETER_FILTER_MIN_PRICE = "max_price";
	public static final String PARAMETER_FILTER_MAX_PRICE= "min_price";
	public static final String PARAMETER_FILTER_STATUS_NUEVO= "status_nuevo";
	public static final String PARAMETER_FILTER_STATUS_USADO= "status_usado";
	public static final String PARAMETER_FILTER_STATUS_REPARADO= "status_reparado";
	public static final String PARAMETER_FILTER_STATUS_AVERIADO= "status_averiado";
	public static final String PARAMETER_FILTER_ORDER_PRICE= "order_price";


	/*Ad*/

	public static final String PARAMETER_AD = "Ad";
	public static final String PARAMETER_AD_REF = "refAd";


	/*Category*/
	public static final String PARAMETER_CATEGORY = "Category";
	public static final String PARAMETER_CATEGORY_ID = "idCate";


	/*SubCategory*/
	public static final String PARAMETER_SUBCATEGORY = "Subcategory";
	public static final String PARAMETER_SUBCATEGORY_ID = "idSubCat";


	/*AC*/

	public static final String PARAMETER_AC = "Ad";
	public static final String PARAMETER_AC_ID = "idAC";

	/*Province*/

	public static final String PARAMETER_PR = "Province";
	public static final String PARAMETER_PR_ID = "idPr";

	/*Locality*/

	public static final String PARAMETER_LOC = "Locality";
	public static final String PARAMETER_LOC_ID = "idPr";

}
