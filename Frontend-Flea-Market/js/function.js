addEventListener('load', loadEvents);

function loadEvents(){
    

    //check-session
     if (window.localStorage.getItem('flea-market') != null) window.sessionStorage.setItem('flea-market',window.localStorage.getItem('flea-market'));
            
     
    (window.sessionStorage.getItem('flea-market') !== null)? load_login(): restart_login();


    //form control
    let form_login= document.getElementById('form-login').addEventListener('submit', request_api_login);
    let form_checkin= document.getElementById('form-checkin').addEventListener('submit', request_api_checkin);
    let form_checkin_submit= document.getElementById('form-checkin__submit').addEventListener('click', control_form_checkin);
    let form_checkin_nick= document.getElementById('form-checkin__nick').addEventListener('change',  check_nick_user);
    let form_checkin_email= document.getElementById('form-checkin__email').addEventListener('change',  check_email_user);
    
    //load event header
    document.getElementById('new-ad-link').addEventListener('click', check_user_registration); 
    document.getElementById('my-ads-link').addEventListener('click', check_user_registration); 
    document.getElementById('my-likes-link').addEventListener('click', check_user_registration); 
   
    //check location
    let urlSearchParams = new URLSearchParams(window.location.search);

    //load search 
    if(urlSearchParams.get('user-ads')!= null){
        let idUser= urlSearchParams.get('user-ads');
        search_by_id_user(idUser);
    }

    //load details
    else if(document.location.href.search('details.html')!=-1){
    
        let refAd= urlSearchParams.get('refAd');

        if(refAd==null ){ location.href="index.html";}
        else {  search_by_refAd(refAd); }

    }

    //load home
    else if(document.location.href.search('home.html')!=-1){
 
       
        let id_user;
   
        if(window.sessionStorage.getItem('flea-market')!=null){

            let id_user=JSON.parse(window.sessionStorage.getItem('flea-market')).id;
          
        }
        else{
            location.href="index.html";
        }
        
     

        
        let section= urlSearchParams.get('section');

        switch(section){


            case "my-account": load_content_my_account();
            break;

            case "my-likes":  resquest_api_my_likes();
            break;

            case "new-ad":  load_content_new_ad();
            break;

            case "my-ads":  resquest_ap_my_ads();
            break;

        }

        change_nav(section);

    }
    //load index
    else {

        load_cat_subCat_list_main();   
       
        document.getElementById('button_search').addEventListener('click',  request_api_filter); 

        load_modal_filter();
    }

}

//search
let records_per_page= 5;
let num_link_pagination=3;
let total_pages;
let search_result;
let connectionAPi;
let connection;
let connectionAPi2;
let time_request;

//filter
let search_filter=null;

//data user
let list_likes_user= null;

//Ref Ad
let ref_like= null;

//check
let check_nick;
let check_email;

//categories
let list_categories;

//Locality new ad
let list_ac;
let list_pr;


//**** INDEX***************


//LOAD LOGIN

    //load login
    function load_login(){

       //data user 
       let tk= JSON.parse(window.sessionStorage.getItem('flea-market')).tk;
       let data_user= atob( tk.split('.')[1]);    
       let email= data_user.split('"')[3];
       let id=  data_user.split('"')[8].substring(1, (data_user.split('"')[8].length)-1);
       let nick= data_user.split('"')[11];

        window.sessionStorage.setItem("flea-market", JSON.stringify({"nick": nick, "email": email, "id": id, "tk": tk}));

        document.getElementById('login-link').classList.add('d-none');
        document.getElementById('login-menu').classList.remove('d-none');
        document.getElementById('login-menu-nick-user').innerHTML= nick;
        document.getElementById('sign-off').addEventListener('click', sign_off); 
    }

    //Sign off
    function sign_off(e){

        if(e!=undefined)e.preventDefault();
        window.sessionStorage.removeItem('flea-market');
        window.localStorage.removeItem('flea-market');
        location.href='index.html';
    }

    //restar login
    function restart_login(){

        document.getElementById('login-link').classList.remove('d-none');
        document.getElementById('login-menu').classList.add('d-none');
        document.getElementById('login-menu-nick-user').innerHTML= "";
    }

    //check user registration
    function check_user_registration(e){

        if (window.sessionStorage.getItem('flea-market') == null){
            e.preventDefault();
            alert("Debe estar registrado");
            
        }

    }




//MODAL FORM LOGIN


    //sep up data login form
    function body_data_request_api_login(){
        
        let email= document.getElementById('form-login__email').value;
        let password= document.getElementById('form-login__password').value;


        return JSON.stringify({email:`${email}`, password:`${password}`});
        
        // let data=`email=${encodeURIComponent(email)}&password=${encodeURIComponent(password)}`; 
        // return data;
    }


   // request api login 
    function request_api_login(e){

        e.preventDefault();
        let email= document.getElementById('form-login__email').value;
        let pss= document.getElementById('form-login__password').value;

        connectionAPi= new XMLHttpRequest();
        connectionAPi.open("POST", "http://3.249.62.209:8080/flea-market/v1/user/login", true);
        connectionAPi.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        connectionAPi.send(body_data_request_api_login());
        connectionAPi.onreadystatechange= request_status_api_login;
    }


    // request status search 
    function request_status_api_login(){
    
        let cont=0;
   
         if(connectionAPi.readyState== 4 && connectionAPi.status == 200){
          
            let tk= connectionAPi.getResponseHeader("authorization");

            window.sessionStorage.setItem("flea-market", JSON.stringify({"tk": tk}));
            window.localStorage.setItem("flea-market",window.sessionStorage.getItem("flea-market"));
            location.href='index.html';

        }

        if(connectionAPi.readyState== 4 && connectionAPi.status == 403){
          
           document.getElementById("form-login__error").innerHTML="usuario o contraseña incorrectos";
   
        }
           
   
   }

   // request api checkin
   function request_api_checkin(){

    alert("Se ha enviado el formulario");
   }




//MODAL FORM CHECKIN

   //check nick user
  async function check_nick_user(){

     let nick= document.getElementById('form-checkin__nick').value;
     
     if(nick!=""){

         let data= await fetch(`http://3.249.62.209:8080/flea-market/v1/user/nick-user/${nick}`); 
         let result= (await data.json())['data'];      
         check_nick= await result;
    }
    else{
        
        check_nick=null;
    }


   }


  //check email user
  async function check_email_user(){

    let email= document.getElementById('form-checkin__email').value;

    if(email!=""){
    let data= await fetch( `http://3.249.62.209:8080/flea-market/v1/user/email-user/${email}`); 
    let result= (await data.json())['data'];
    check_email= await result;
    }
    else{
        check_email=null;
    }
  }


  //check password user
  function check_password_user(password){

    let exp = /^[a-zA-Z]+$/;
    let character;
    let letter=0;
    let number=0;
    let special_char=0;

    for (i = 0; i < password.length; i++)
	{
		character  = password.charAt(i);
		if (exp.test(character)){letter++;}
	  	else if (!isNaN(character)){number++;}
        else {special_char++}
	}

    return (letter>0 && number>0 && special_char>0)? true: false;
  }

  //check form checkin
  function control_form_checkin(){

    let nick= document.getElementById('form-checkin__nick');
    let email= document.getElementById('form-checkin__email');
    let pss= document.getElementById('form-checkin__password');
    let pss2= document.getElementById('form-checkin__password2');

    
    //nick
    (!check_nick)? nick.setCustomValidity("") : nick.setCustomValidity("Este nick ya esta en uso."); 

    //email
    (!check_email)? email.setCustomValidity("") : email.setCustomValidity("Este correo ya esta registrado.");

    //Pss
    (check_password_user(pss.value))?pss.setCustomValidity("") :
    pss.setCustomValidity("Formato de contraseña incorrecto:\n-Debe tener entre 8 y 15 caracteres\n-Alfanumérica\n-Al menos un caracter especial"); 
        
    //Pss2
    (pss.value== pss2.value )? pss2.setCustomValidity("") : pss2.setCustomValidity("Las contraseñas no coinciden"); 



  }

  //request api checkin
  function request_api_checkin(e){

        e.preventDefault();

        let nick= document.getElementById('form-checkin__nick').value;
        let name= document.getElementById('form-checkin__name').value.trim();
        let phone= document.getElementById('form-checkin__phone').value;
        let email= document.getElementById('form-checkin__email').value;
        let password= document.getElementById('form-checkin__password').value;


        connectionAPi= new XMLHttpRequest();
        connectionAPi.open("POST", "http://3.249.62.209:8080/flea-market/v1/user/check-in", true);
        connectionAPi.setRequestHeader("Content-Type","application/json");
        connectionAPi.send( JSON.stringify({nickUser:`${nick}`, emailUser:`${email}`,
         passwordUser:`${password}`,  nameUser:`${name}`, phoneUser:`${phone}` }));
        connectionAPi.onreadystatechange= ()=>{

            if(connectionAPi.readyState== 4){
   

                if(connectionAPi.status== 200){

                    connectionAPi= new XMLHttpRequest();
                    connectionAPi.open("POST", "http://3.249.62.209:8080/flea-market/v1/user/login", true);
                    connectionAPi.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
                    connectionAPi.send( JSON.stringify({email:`${email}`, password:`${password}`}));
                    connectionAPi.onreadystatechange= request_status_api_login;

                }
                else{
                    alert("Se ha producido un error al realizar el registro")
                    window.location.href='index.html';
                }

            }


        }

  }



//MODAL FILTER

//load modal filter
function load_modal_filter(){

    let button_modal_submit= document.getElementById('modal-filter__submit').addEventListener('click', request_api_filter);
    let button_modal_reset= document.getElementById('modal-filter__reset').addEventListener('click',reset_form_new_ad);
    request_api_categories();
    request_api_ac();
}


//request api filter
function request_api_filter(e){
    e.preventDefault();
    search_filter= document.getElementById('input_search').value;
    let cat= document.getElementById('form-new-ad__cat').value;
    let subcat= document.getElementById('form-new-ad__subCat').value;
    let ac= document.getElementById('form-new-ad__ac').value;
    let pr= document.getElementById('form-new-ad__pr').value;
    let lo= document.getElementById('form-new-ad__lo').value;
    let min_price= document.getElementById('modal-filter__price__min').value;
    let max_price= document.getElementById('modal-filter__price__max').value;
    let status_nuevo= (document.getElementById('modal-filter__item-status-nuevo').checked)? true: false;
    let status_usado= (document.getElementById('modal-filter__item-status-usado').checked)? true: false;
    let status_reparado= (document.getElementById('modal-filter__item-status-reparado').checked)? true: false;
    let status_averiado= (document.getElementById('modal-filter__item-status-averiado').checked)? true: false;
    let order_price= document.getElementsByName('modal-filter__order-price');

    
    for(i = 0; i <order_price.length; i++) {
       
        if(order_price[i].checked){
            order_price=order_price[i].value;
              break;
        }
    }

    if(cat=="-Selecciona-") cat= -1;
    if(subcat=="-Selecciona-") subcat=-1;
    if(ac=="-Selecciona-") ac= -1;
    if(pr=="-Selecciona-") pr=-1;
    if(lo=="-Selecciona-") lo=-1;
    if(min_price=="")min_price=-1;
    if(max_price=="")max_price=-1;
    

    connectionAPi= new XMLHttpRequest();
    connectionAPi.open("GET",
    `http://3.249.62.209:8080/flea-market/v1/ad/list-ad-filter?search=${search_filter}&cat=${cat}&subcat=${subcat}&ac=${ac}&pr=${pr}&lo=${lo}
    &min_price=${min_price}&max_price=${max_price}&status_nuevo=${status_nuevo}&status_usado=${status_usado}&status_reparado=
    ${status_reparado}&status_averiado=${status_averiado}&order_price=${order_price}`
    ,true); 
    connectionAPi.send();

    connectionAPi.onreadystatechange= ()=>{

        if(connectionAPi.readyState== 4){


            if(connectionAPi.status== 200){
                 

                search_result=JSON.parse(connectionAPi.responseText).data;
                load_ad_search_result();
            
            }
        }

    }



}


// LOAD CATEGORIES AND SUBCATEGORIES 


    //assign function to links categoties and subcategoties 
    function assign_function_to_cat_subcat(){

        array_link_cat= document.getElementsByClassName('link_cat');

        for (let i = 0; i < array_link_cat.length; i++) {
            const link_cat = array_link_cat[i];

            link_cat.addEventListener('click', search_by_category);

            
        }

        array_link_subCat= document.getElementsByClassName(' link_subCat');

        for (let i = 0; i < array_link_subCat.length; i++) {
            const link_subCat = array_link_subCat[i];

            link_subCat.addEventListener('click', search_by_subCat);
            
        }
    
    }
    

    //load categories and subcategories list main
    function load_cat_subCat_list_main(){
        
        request_api_load_cat_and_subcat("http://3.249.62.209:8080/flea-market/v1/cat/list-all-and-sub");

    }
        
    //request api for load categories and subcategories
    function request_api_load_cat_and_subcat($url){
        connectionAPi2= new XMLHttpRequest();
        connectionAPi2.open("GET", $url, true);
        connectionAPi2.send();
        connectionAPi2.onreadystatechange = request_status_load_cat_and_subcat;
        time_request= setTimeout(abort_request, 5000); 
    }

        
    //request status api for load categories and subcategories
    function  request_status_load_cat_and_subcat(){

        let main_index= document.getElementById("main-index");
        main_index.innerHTML="";

        if(connectionAPi2.readyState== 4 && connectionAPi2.status == 200){
            let result= JSON.parse(connectionAPi2.responseText);
        
                clearTimeout(time_request);

                if(result['message']!= "OK"){

                    main_index.insertAdjacentHTML("afterbegin","<p class='text-center text-primary fs-5'>Se ha producido un error del servicio. Sentimos las molestias</p>");  
                }

                else if(result['data'].length == 0){
                
                    main_index.insertAdjacentHTML("afterbegin", "<p class='text-center text-primary fs-5'>No se han encontrado resultados</p>");

                }
                else{
                
                        let category;
                        let idCAt;
                        let nameCat;
                        let subCat;
                        let idSubCat;
                        let nameSubCat;
                        let subCatList="";
                        let arrayCategories="";
                        let content="";

                        for (let i= 0; i< result['data'].length; i++) {
                
                        category = result['data'][i];
                        nameCat= category['nameCat'];
                        idCAt= category['idCAt'];

                        nameCat= `<a class="link_cat text-decoration-none fs-5 fw-bolder" href="#" id="${idCAt}">${nameCat}</a>` ;
                        
        
                        for (let b= 0; b< category['subCatList'].length; b++) {

                                    subCat=category['subCatList'][b];
                                    idSubCat=subCat['idSubCat'];
                                    nameSubCat=subCat['nameSubCat'];

                                    if(b != category['subCatList'].length-1){

                                        subCatList= subCatList.concat(`<a class="link_subCat pe-2 text-decoration-none text-dark fs-5"  id="${idSubCat}"  href="#">${nameSubCat},</a>`); 
                                    }
                                    else{

                                        subCatList= subCatList.concat(`<a class="link_subCat pe-2 text-decoration-none text-dark fs-5"  id="${idSubCat}"  href="#" >${nameSubCat}</a>`);
                                    }
                                
                        }


                        let class_card="main-index__categories__card col d-flex  flex-column  align-items-center my-0 my-md-2   p-2   rounded shadow-lg ";
                        let class_cat= "main-index__categories__card__cat my-2 fs-3 fw-bolder";
                        let class_subcat="main-index__categories__card__subcat d-flex flex-wrap justify-content-center  fs-6";

                        arrayCategories= arrayCategories.concat( 
                        `<section class=" ${class_card}">
                                <div class="${class_cat}">${nameCat}</div>
                                <div class="${class_subcat}">${subCatList}</div>
                        </section>`);
                        
                        subCatList="";

                        }


                        let class_title="main-index__title mb-5 text-center fs-1 fw-bolder ";
                        let class_categories="main-index__categories row  d-flex  flex-md-row justify-content-between gap-4 p-0 aling-items-center ";

                        let title= ` <p class="${class_title}">Todo lo que buscas</p>`;
                        let categories= `<div class="${class_categories}"> ${arrayCategories}</div>`;

                        content= title + categories; 

                        main_index.insertAdjacentHTML("beforeend", content);

                        assign_function_to_cat_subcat();
                }
        
            }

            else{

                main_index.insertAdjacentHTML( "afterbegin","<p class='text-center text-primary fs-5'>Esperando resultados...</p>");
            }

    }





//SEARCH 


  //prevent event
    function preventDefault(e){

       e.preventDefault();
       
    }

   //ad event link page
    function  add_event_page_link(location){
    
    let fn;  
    switch (location){

        case "index.html": fn= load_ad_search_result; break;
        case "home.html?section=my-likes": fn= load_result_my_likes; break;
        case "home.html?section=my-ads": fn= load_result_my_ads; break;

    }
    
    let array_page_link= document.getElementsByClassName('page-link');

    let total_pages= Math.ceil(search_result.length/records_per_page);

    for (let i = 0; i < array_page_link.length; i++) {
        const page_link = array_page_link[i];

         page_link.addEventListener('click', fn);
       
     
    }
    
    }


   //insert container pagination
    function insert_container_pagination(pag, location="index.html"){


    let main= (location=="index.html")? "main-index": "main-home";

    pag= parseInt(pag);
    let pag_present= pag;
    total_pages= Math.ceil(search_result.length/records_per_page);
    let main_location= document.getElementById(main);
    let nav_pagination= document.getElementById('nav-pagination');

    if(nav_pagination!= null){ nav_pagination.remove(); }

    let num_link=0;

     if(pag >=3 &&  pag == total_pages ){pag-=2;}
     if(pag>=2 && pag+1>=total_pages){pag--;}

    
    let link_pages="";

    do{

        if( (pag + num_link)<=total_pages){


            if( (pag + num_link)==pag_present){

                link_pages= link_pages.concat(`<li class="page-item"><a class="page-link link-primary fw-bolder" id="${(pag + num_link)}" href="#start-page">${(pag + num_link)}</a></li>`);
            }
           else{

                link_pages= link_pages.concat(`<li class="page-item "><a class="page-link link-dark " id="${(pag + num_link)}" href="#start-page">${(pag + num_link)}</a></li>`);
            }
           
        }
        
        num_link++;

    }while(num_link< num_link_pagination);




    let container_pag= 
        `<nav id="nav-pagination" aria-label="pagination_search">
            <ul class="pagination justify-content-center">
                
                <li class="page-item ">
                    <a class="page-link " id="${pag_present-1}" href="#start-page">Anterior</a>
                </li>
                            
                ${link_pages}
                            
                <li class="page-item">
                    <a class="page-link " id="${pag_present+1}" href="#start-page">Siguiente</a>
                </li>

            </ul>
        </nav>`;  
       
    (location!="main-index")? main_location.insertAdjacentHTML("beforeend", container_pag): main_location.insertAdjacentHTML("afterend", container_pag);
        
    add_event_page_link(location);
   
    }


    //search by idUser
    function search_by_id_user(idUser){

        request_api_search_by_cat_or_subcat(`http://3.249.62.209:8080/flea-market/v1/ad/list-ad-user/${idUser}`);
    
    }

    //search by category
    function search_by_category(e){

        e.preventDefault();
        request_api_search_by_cat_or_subcat(`http://3.249.62.209:8080/flea-market/v1/ad/list-ad-cat/${e.target.id}`);
    
    }
    

    //search by subcategory
    function search_by_subCat(e){
    
        e.preventDefault();
        request_api_search_by_cat_or_subcat(`http://3.249.62.209:8080/flea-market/v1/ad/list-ad-sub-cat/${e.target.id}`);
    
    }
    
      
    //request api search by category or subcategory
    function  request_api_search_by_cat_or_subcat(url){
        connectionAPi= new XMLHttpRequest();
        connectionAPi.open("GET", url, true);
        connectionAPi.send();
        connectionAPi.onreadystatechange = request_status_search;
        time_request= setTimeout(abort_request, 5000); 
    }
  
    //request api likes user
    function  request_api_likes_user(){

        return new Promise((res, rej) =>{

            let id_user=JSON.parse(window.sessionStorage.getItem('flea-market')).id;
            connectionAPi= new XMLHttpRequest();
            connectionAPi.open("GET", `http://3.249.62.209:8080/flea-market/v1/ad/list-ad-like-user/${id_user}`, true);
            connectionAPi.send();
            connectionAPi.onreadystatechange= ()=>{

        
                if(connectionAPi.readyState== 4 && connectionAPi.status == 200){
                
                    let result= JSON.parse(connectionAPi.responseText);
                    
                            list_likes_user= [];
                        
                        if(result['message']== "OK"){
            
                            let data= result['data'];

                            for(let i=0; i<data.length; i++){

                                let refAd= data[i]['refAd'];

                                list_likes_user.push(refAd);

                            }             
                            res(list_likes_user);   
                        }
            
                }

            }

        });
      


    }
  

    //add event ad´s likes
    function add_event_ad_likes(){

      let array_like_ad= document.getElementsByClassName('like-ad');

       
      for (let i=0; i< array_like_ad.length; i++) {
        const element = array_like_ad[i];

        element.addEventListener('click',change_interest_ad);

      }

    }

    //change interest ad by user
    function change_interest_ad(e){

        e.preventDefault();

        let id_user= (window.sessionStorage.getItem('flea-market')!=null)? JSON.parse(window.sessionStorage.getItem('flea-market')).id: null;
        
        if(id_user == null){

            alert("Debes de estar registrado");
        }
        else{

            let refAd= e.target.parentNode.id;
            ref_like= refAd;
            let like= (e.target.parentNode.classList.contains('like'))? true: false;
            (like)? request_api_change_like("DELETE", refAd,id_user): request_api_change_like("POST",refAd,id_user);
        }

    }

  
    //request api change like
    function request_api_change_like(method,refAd,id_user){
        
    
        connectionAPi= new XMLHttpRequest();
        connectionAPi.open(method, `http://3.249.62.209:8080/flea-market/v1/user/like/id-user/${id_user}/ref-ad/${refAd}`, true);
        connectionAPi.setRequestHeader('Authorization', `${JSON.parse(window.sessionStorage.getItem('flea-market')).tk}`);      
        connectionAPi.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        connectionAPi.send();
        connectionAPi.onreadystatechange= request_status_api_change_like;
    }

    //request status api change like
    function request_status_api_change_like(){


        if(connectionAPi.readyState==4 && connectionAPi.status == 200){

            let result= JSON.parse(connectionAPi.responseText);
            let link= document.getElementById(`${ref_like}`);

            if(result['code'] == "201 CREATED"){
            
                link.classList.remove('noLike');
                link.classList.add('like');
                link.innerHTML="<i class='fa-solid fa-heart'></i>";
                ref_like=null;

            }


            if(result['code'] == "204 NO_CONTENT"){

                link.classList.remove('like');
                link.classList.add('nolike');
                link.innerHTML="<i class='far fa-heart'></i>";
                ref_like=null;

            }

           

        }

      

    }


    //load ad search result
  async  function load_ad_search_result(e){
    
        if(e!=null) e.preventDefault();
        

        if(window.sessionStorage.getItem('flea-market')!=null){    
            
            id_user=JSON.parse(window.sessionStorage.getItem('flea-market')).id;
            let likes= await request_api_likes_user(); 
        
        }else{
            id_user=null;
        }

        let page= (e== null  || e.target.id<1)?  1 : e.target.id; 

        if(page-1== total_pages)page=total_pages;
     
        let main_index= document.getElementById("main-index");
        main_index.innerHTML=`<a name="main"></a>`;

        let class_ad_card= "main-index__ad-card row d-flex flex-column align-items-center flex-md-row mb-4 border border-3 border-secondary rounded bg-secondary ";
        let class_card_img="main-index__ad-card__img d-flex justify-content-center col-md  align-content-md-center mt-2 mb-3 my-md-0";
        let class_card_inf="main-index__ad-card__inf w-100  col-md ";
        let class_info_title="main-index__ad-card__inf__title overflow-hidden fs-5 fw-bolder text-lowercase"
        let class_info_priceAndStatus="main-index__ad-card__inf__price-and-status d-flex justify-content-between align-items-center ";
        let class_info_description= "main-index__ad-card__inf__description overflow-hidden fs-6";
        let class_info_localityAndLike="main-index__ad-card__inf__localityAndLike d-flex justify-content-between mt-1 align-items-center mt-3 ";


        // <i class="fa-solid fa-heart"></i>

       let icono;
        
        for (let i= (page-1)*records_per_page; i< (page * records_per_page); i++) {

         if(i ==search_result.length){break;}

          let ad = search_result[i];
          let refAd= ad['refAd'];
          let user_id_ad= ad['userId'];
          let title= ad['titleAd'];
          let urlMainPhoto= ad['photoList'][0]['urlPhoto'];
          let itemStatus= ad['itemStatusAd'];
          let description= ad['descriptionAd'];
          let price= ad['priceAd'];
          let locality= ad["idLo"];
          
          
          let icono_link;
          let heart_solid="<i class='fa-solid fa-heart'></i>";
          let heart_hollow="<i class='far fa-heart'></i>";
          let noLike=true;

          //check user registration 
          if(id_user!= null){

            if(id_user==user_id_ad){

                icono_link= "";
            }
            else{
                
                for (let i = 0; i <list_likes_user.length; i++) {
                    
                    const ref = list_likes_user[i];
                    
                    if(ref== refAd){ noLike=false;}
                }

                icono_link= (noLike)? 
                `<a href="#" class="fs-2 link-danger noLike like-ad"  id="${refAd}" >${heart_hollow}</a>`:
                `<a href="#" class="fs-2 link-danger like like-ad"  id="${refAd}" >${heart_solid}</a>`;
            }

          }
          else{

               icono_link= `<a href="#" class="fs-2 link-danger noLike like-ad" id="${refAd}" >${heart_hollow}</a>`;
          }
         
             
          let div_ad_card= 
            `<div class="${class_ad_card}">
              
                <div class="${class_card_img}">
                                    
                     <a href="details.html?refAd=${refAd}" target="_blank" >
                        <img class="align-self-center "  src="${urlMainPhoto}" alt="img-main"></img>
                     </a>

                </div>
    
                <div class="${class_card_inf}">
                                
                    <p class="${class_info_title}">${title}</p>
    
                    <div class="${class_info_priceAndStatus}">
                                        
                        <p class="fs-5  fw-bolder">${price}€</p>
                        <p class="fs-6 text-primary text-uppercase">${itemStatus}</p>
                                        
                    </div>
    
                    <div class="${class_info_description}">
                        <p>${description}</p>
                    </div>
                                        
                     <div class="${class_info_localityAndLike}">

                         <a href="https://www.google.es/maps/search/${locality}" target="_blank" >${locality}</a>  
                         ${icono_link}

                     </div>
    
                </div>
                                
                        
            </div>`;    
        
          main_index.insertAdjacentHTML("beforeend", div_ad_card);
          
        }

        if(search_result.length!=0){
            insert_container_pagination(page);
        } else {
            main_index.innerHTML=`<a name="main"> <p class='text-center text-primary fs-5'>No se han encontrado resultados para esta busqueda</p></a>`;
        }
        add_event_ad_likes();
      
    }
           
    // request status search 
    function request_status_search(){
        
     

         let main_index= document.getElementById("main-index");
         main_index.innerHTML="";
    
          if(connectionAPi.readyState== 4 && connectionAPi.status == 200){
             let result= JSON.parse(connectionAPi.responseText);
          
                  clearTimeout(time_request);
    
                if(result['message']!= "OK"){
    
                    main_index.insertAdjacentHTML("afterbegin","<p class='text-center text-primary fs-5'>Se ha producido un error del servicio. Sentimos las molestias</p>");  
                }
    
                else if(result['data'].length == 0){
                   
                    main_index.insertAdjacentHTML("afterbegin", "<p class='text-center text-primary fs-5'>No se han encontrado resultados para esta busqueda</p>");
    
                }
    
                else{
                 
                    search_result=result['data'];
                    load_ad_search_result();

                }
    
    
            }
            
            else{
    
                main_index.insertAdjacentHTML( "afterbegin","<p class='text-center text-primary fs-5'>Esperando resultados...</p>");
            }
    
    }




//***** DETAILS**********


    //calculate_days
    function calculate_days(date_update){

        let difference= Math.abs(new Date().getTime()-date_update.getTime());
 
    return Math.round(difference/(864*Math.pow(10,5)));
 
    }


    //search by refAd
    function search_by_refAd(ref){
            
        request_api_ad_details(`http://3.249.62.209:8080/flea-market/v1/ad/${ref}`);

    }


    //request api ad details
    function  request_api_ad_details(url){
        connectionAPi= new XMLHttpRequest();
        connectionAPi.open("GET", url, true);
        connectionAPi.send();
        connectionAPi.onreadystatechange =request_status_ad_details;
    }

    // request status search 
    function request_status_ad_details(){
       
             if(connectionAPi.readyState== 4 && connectionAPi.status == 200){
                let result= JSON.parse(connectionAPi.responseText);
             
                   if(result['message']== "OK"){
                    load_ad_details(result['data']);  
                   }
       
               }
               
        
       
    }


    //request status user´s ads 
    function request_status_user_ads(){
    
        if(connectionAPi.readyState== 4 && connectionAPi.status == 200){
           let result= JSON.parse(connectionAPi.responseText);
        
                    
                document.getElementById('lastUpdateAd').innerHTML=`Publicaciones  ${result['data'].length}`;
                document.getElementById('lastUpdateAd').setAttribute('href',`index.html?user-ads=${result['data'][0]['userId']}`); 
  
          }
  
   }

   //request api  user´s ads 
   function request_api_user_ads(idUser){

       connectionAPi= new XMLHttpRequest();
       connectionAPi.open("GET", `http://3.249.62.209:8080/flea-market/v1/ad/list-ad-user/${idUser}`, true);
       connectionAPi.send();
       connectionAPi.onreadystatechange =request_status_user_ads;
   }


    //request status user data
    function request_status_user_data(){
    
         if(connectionAPi.readyState== 4 && connectionAPi.status == 200){
            let result= JSON.parse(connectionAPi.responseText);
         
               let data_user=(result['data']);
               let date= new Date(data_user["dischargeDateUser"]);
               let date_string= `${date.getDate()}/${date.getMonth()}/${date.getFullYear()}`;


                document.getElementById('nickUser').innerHTML= data_user['nickUser'];
                document.getElementById('emailUser').innerHTML= data_user['emailUser'];
                document.getElementById('emailUser').setAttribute('href',`mailto:${data_user['emailUser']}`); 
                document.getElementById('dischargeDateUser').innerHTML= date_string;

              
                if(data_user['nameUser']!=null){
                   document.getElementById('nameUser').innerHTML= data_user['nameUser'];
                }

                if(data_user['phoneUser']!=null){
                   document.getElementById('phoneUser').innerHTML= data_user['phoneUser'];
                }

                request_api_user_ads(data_user['idUser']);

           }
   
           
    }

    //request api user date
    function request_api_user_data(idUser){

        connectionAPi= new XMLHttpRequest();
        connectionAPi.open("GET", `http://3.249.62.209:8080/flea-market/v1/user/short/${idUser}`, true);
        connectionAPi.send();
        connectionAPi.onreadystatechange =request_status_user_data;
    }


    //load ad details
   async function load_ad_details(ad){


        if(window.sessionStorage.getItem('flea-market')!=null){
            
            let likes= await request_api_likes_user();

            id_user= JSON.parse(window.sessionStorage.getItem('flea-market')).id;

                       
        }else{

            id_user=null;

        }

        let refAd= ad['refAd'];
        let userId= ad['userId'];
        let title= ad['titleAd'];
        let arrayPhotos= ad['photoList'];
        let itemStatus= ad['itemStatusAd'];
        let description= ad['descriptionAd'];
        let price= ad['priceAd'];
        let locality= ad["idLo"];
        let update=  calculate_days(new Date(ad["lastUpdateAd"]));
        let numLikes= ad ["numLikes"];
      

        let class_details= "main-index__details row d-flex flex-column align-items-center  pt-3 border-secondary rounded ";
       // let class_details_img="main-index__details__img d-flex justify-content-center col-md  align-content-md-center mb-5 ";
        let class_details_inf="main-index__details__inf w-100  col  d-lg-flex mb-2 mt-4  ";
        let class_inf_left="main-index__details__inf__left";
        let class_left_title="main-index__details__inf__left__title overflow-hidden fs-5 fw-bolder text-lowercase"
        let class_left_description= "main-index__details__inf__left__description fs-6";
        let class_left_locality="main-index__details__inf__left__locality  ";
        let class_left_update="main-index__details__inf__left__update mt-2";
        let class_inf_right="main-index__details__inf__right  ms-lg-2  d-flex justify-content-between align-items-center flex-lg-column  align-items-lg-end  ";
        let class_right_itemStatus="main-index__details__inf__right__itemStatus fs-6 text-primary text-uppercase p-0 m-0 mt-lg-5 pt-lg-2";
        let class_right_like="main-index__details__inf__right__like link-danger like-detail fs-2 mb-lg-1";
        let class_like_numlike="main-index__details__inf__right__like__numLike text-dark fs-5 ";

        let flag= false;

        let icono;
        if(id_user!=null){

            for (let i = 0; i <list_likes_user.length; i++) {
                  
                const ref = list_likes_user[i];
                
                if(ref== refAd){ flag=true;}
            }

        icono= (flag)? "<i class='fa-solid fa-heart'></i>": "<i class='far fa-heart'></i>";

        }else{

            icono= "<i class='far fa-heart'></i>";
        }

    
        let carrusel_item="";

        let carousel_indicators="";

        for (let i= 0; i< arrayPhotos.length; i++) {
            const photo = arrayPhotos[i]['urlPhoto'];

            let active= (i==0)? "active": "";

            carrusel_item= carrusel_item.concat(`
          
            <div class="carousel-item ${active} ">
              <img src="${photo}" class="d-block w-100" alt="...">
            </div>
           `);


           let main_item= (i==0)? 'class="active" aria-current="true" ': '';

           carousel_indicators= carousel_indicators.concat(`
             <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="${i}" aria-label="Slide ${i}" ${ main_item} ></button>
           `);

            
        }

  
        let carrusel= `
        
        <div id="carouselExampleIndicators" class="carousel slide " data-bs-ride="carousel">
           
            <div class="carousel-indicators">
               ${carousel_indicators}
            </div>

            <div class="carousel-inner">
            ${carrusel_item}
            </div>

            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Anterior</span>
            </button>
            
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Siguiente</span>
            </button>

            </div>`;

             
        let div_details= 
            `<div class="${class_details}">
              
                ${carrusel}       

                <div class="${class_details_inf}">

                    <div class="${class_inf_left}">
                                
                        <p class="${class_left_title}">${title}</p>
                        
                        <p class="fs-5  fw-bolder">${price}€</p>
                          
                        <p class="${class_left_description}">${description}</p>

                        <a href="https://www.google.es/maps/search/${locality}"  class="${class_left_locality}" target="_blank" >${locality}</a> 
                        
                        <p class="${class_left_update}">Última actualizacíon hace ${update} días</p>
                          
                    
                    </div>

                    <div class="${class_inf_right}">

                    <p class="${class_right_itemStatus}">${itemStatus}</p>
                    <a href="#" class="${class_right_like}" id="${refAd}">${icono} <span class="${class_like_numlike}" id="numLikes">${numLikes}</span></a>
                    

                    </div>
    
                </div>
                                
                        
            </div>`;    
        
        let user_details=  document.getElementById('main-index__user-details');
        user_details.insertAdjacentHTML("beforebegin",div_details );
        request_api_user_data(userId);

        add_event_like_details();

      
    }


    //add event like detail
    function add_event_like_details(){

        let array_like_ad= document.getElementsByClassName('like-detail');
 
        for (let i=0; i< array_like_ad.length; i++) {
          const element = array_like_ad[i];  
          element.addEventListener('click',change_interest_ad_detail);
  
        }

    }

    //change interest like detail
    function change_interest_ad_detail(e){


        e.preventDefault();

        if(window.sessionStorage.getItem('flea-market')!=null){
            
            id_user= JSON.parse(window.sessionStorage.getItem('flea-market')).id;

            let refAd= e.target.parentNode.id;
            let icon=  e.target;
            let span_likes= document.getElementById('numLikes');
            let cant_likes= parseInt(span_likes.innerHTML);
            let like=  (icon.getAttribute("class")=="far fa-heart")? false: true;
            let method= (like)? "DELETE" : "POST";

            //request api add/ delete lile

            connectionAPi= new XMLHttpRequest();
            connectionAPi.open(method, `http://3.249.62.209:8080/flea-market/v1/user/like/id-user/${id_user}/ref-ad/${refAd}`, true);
            connectionAPi.setRequestHeader('Authorization', `${JSON.parse(window.sessionStorage.getItem('flea-market')).tk}`);      
            connectionAPi.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            connectionAPi.send();
            connectionAPi.onreadystatechange= ()=>{

                
                if(connectionAPi.readyState==4 && connectionAPi.status == 200){

                    let result= JSON.parse(connectionAPi.responseText);

                    if(result['code'] == "201 CREATED"){
                    
                        icon.removeAttribute('class'); 
                        icon.setAttribute('class','fa-solid fa-heart');
                        span_likes.innerHTML= `${cant_likes +1}`;
                    
                    }

                    if(result['code'] == "204 NO_CONTENT"){

                        icon.removeAttribute('class'); 
                        icon.setAttribute('class','far fa-heart');
                        span_likes.innerHTML= `${cant_likes -1}`;

                    }

                }


            }


        }else{
            alert("Debe estar registrado");
        }




    }

    


//******** HOME *******************


//change nav
function change_nav(section){

    let array_link= document.getElementById('nav-home__links').getElementsByTagName('a');

    for (let i = 0; i< array_link.length; i++) {
        const a = array_link[i];

        (a.getAttribute('href').endsWith(section))? a.classList.add('fw-bolder'): a.classList.remove('fw-bolder');
        
    }

}



//MY-ACCOUNT

//load content my count
function load_content_my_account(){

    //load content
    let main_home= document.getElementById("main-home").innerHTML= `  
    
    <!-- Data and Setting -->
    <div class="main-home__my-data ">

        <form action="home.html" id="form-update-user" > 

            <!-- Data -->
            <div class="main-home__my-data__data" >
               
                <p class="title fs-5 fw-bolder">Mis Datos</p>

                <div class="form-data bg-secondary p-4 ">

                    <!-- Nick control -->
                    <div class="input-group flex-nowrap mb-4 ">
                            <span class="input-group-text" id="lavel-nick">Nick:</span>
                            <input type="text" class="form-control " id="form-update-user__nick"  aria-label="nick" maxlength="20" readonly>
                    </div>


                    <!-- email control -->
                    <div class="input-group flex-nowrap mb-4 ">
                        <span class="input-group-text" id="icon-email">Correo:</span>
                        <input type="email" class="form-control " id="form-update-user__email"  aria-label="email" readonly>
                    </div>

                    <!-- Name control -->
                    <div class="input-group flex-nowrap mb-4 ">
                        <span class="input-group-text" id="icon-name">Nombre:</span>
                        <input type="text" class="form-control " id="form-update-user__name"  aria-label="name"  maxlength="50" tittle="Máximo 50 caracteres" placeholder required>
                    </div>


                    <!-- Phone control -->
                    <div class="input-group flex-nowrap mb-4 ">
                        <span class="input-group-text" id="id-phone">Móvil:</span>
                        <input type="text" class="form-control" id="form-update-user__phone"  aria-label="phone"  pattern="^[0-9]{9}$" title="Debe ser un número de 9 dígitos" placeholder required>
                    </div>

                                    

                </div>                                
            </div>

            <!-- Setting -->
            <div class="main-home__my-data__setting mt-4 " >

                <p class="title fs-5 fw-bolder">Ajustes</p>

                <div class="form-setting bg-secondary p-4">

                   <!-- name control -->
                   <div class="form-check form-switch">
                       <input class="form-check-input" type="checkbox" value="" id="form-update-user__show-name">
                       <label class="form-check-label" for="show-name">Mostrar mi nombre</label>
                   </div>

                    <!-- email control -->
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox" value="" id="form-update-user__show-email">
                        <label class="form-check-label" for="show-email">Mostrar mi correo</label>
                    </div>

                    <!-- phone control -->
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox" value="" id="form-update-user__show-phone">
                        <label class="form-check-label" for="show-phone">Mostrar mi número de móvil</label>
                    </div>
                            
                </div>

                <div class="main-home__my-data__submit d-flex flex-column align-items-center flex-md-row justify-content-md-between my-4">
                    <span  class="fs-5 text-primary mb-3 mb-md-0" id="form-update-user__message"></span>
                    <button class="btn btn-primary text-white" id="form-update-user__submit" type="submit">Actualizar</button>
                </div>  


            </div>     

        </form>

    </div>


    <!-- Security -->
    <div class="main-home__security ">

        <form action="home.html" id="form-update-password"> 

            <p class="title fs-5 fw-bolder">Seguridad</p>

            <div class="form-security bg-secondary p-4">

                <!-- old password control -->
                    <div class="input-group flex-nowrap  mb-4 ">
                        <span class="input-group-text" id="icon-password">Contraseña Actual:</span>
                        <input type="password" class="form-control input-sm"  id="form-update-password__old-password"   aria-label="password"
                        minlength="8" maxlength="15" title="Debe tener de 8 a 15 caracteres. Alfanumérica. Al menos un caracter especiál."  placeholder required>  
                    </div>


                <!-- new password control -->
                    <div class="input-group flex-nowrap  mb-4 ">
                        <span class="input-group-text" id="icon-password">Nueva Contraseña</span>
                        <input type="password" class="form-control" id="form-update-password__new-password"  aria-label="password"
                        minlength="8" maxlength="15" title="Debe tener de 8 a 15 caracteres. Alfanumérica. Al menos un caracter especiál."  placeholder required>  
                    </div>
            
    
                    <!--new  password2 control -->
                    <div class="input-group flex-nowrap ">
                        <span class="input-group-text" id="icon-password2">Repita Contraseña</span>
                        <input type="password" class="form-control" id="form-update-password__new-password2"  aria-label="password2"
                        minlength="8" maxlength="15" placeholder required>
                    </div>

            </div>

            <div class="main-home__security__submit d-flex flex-column align-items-center flex-md-row justify-content-md-between my-4 ">
                <span  class="fs-5 mb-3 mb-md-0" id="form-update-password__message"></span>
                <button class="btn btn-primary text-white" id="form-update-password__submit" type="submit">Actualizar</button>
            </div> 
        
        </form>

        
    </div> 

    <!-- Delete  account-->
    <div class="main-home__delete-account d-flex justify-content-center mt-5">

        <button class="btn btn-danger text-white mt-5 mt-md-0" data-bs-toggle="modal" data-bs-target="#modal-delete-account" type=" button">Eliminar Cuenta</button>

    </div>
    
    `;

    //load event
    let form_update_user= document.getElementById('form-update-user').addEventListener("submit", request_api_update_user);
    let form_password= document.getElementById('form-update-password').addEventListener("submit",request_api_update_password);
    let form_password_submit= document.getElementById('form-update-password__submit').addEventListener("click",check_form_update_password);
    let button_delete_account= document.getElementById('button-delete-account').addEventListener('click', request_api_delete_account);

    //load data
    load_my_account();

}

//load my count
function load_my_account(udt=""){

    let nick= document.getElementById('form-update-user__nick');
    let email= document.getElementById('form-update-user__email');
    let name= document.getElementById('form-update-user__name');
    let phone= document.getElementById('form-update-user__phone');
    let show_phone= document.getElementById('form-update-user__show-phone');
    let show_name= document.getElementById('form-update-user__show-name');
    let show_email= document.getElementById('form-update-user__show-email');
    let form_update_user_message= document.getElementById('form-update-user__message');
    let form_update_password_message= document.getElementById('form-update-password__message');
    form_update_user_message.innerHTML="";
    form_update_password_message.innerHTML="";


    let id_user=  JSON.parse(window.sessionStorage.getItem('flea-market')).id;
    connectionAPi= new XMLHttpRequest();
    connectionAPi.open("GET", `http://3.249.62.209:8080/flea-market/v1/user/medium/${id_user}`, true);
    connectionAPi.send();
    connectionAPi.onreadystatechange = ()=>{


        if(connectionAPi.readyState==4){


            if(connectionAPi.status==200){

                let result= (JSON.parse(connectionAPi.responseText))['data'];

                //load data user
                nick.value=result['nickUser'];
                email.value=result['emailUser'];
                name.value=result['nameUser'];
                phone.value=result['phoneUser'];
                if(result['showEmailUser']) show_email.checked=true;
                if(result['showNameUser']) show_name.checked=true;
                if(result['showPhoneUser']) show_phone.checked=true;

                switch(udt){

                    case "udtUserTrue": form_update_user_message.innerHTML= "Se ha actualizado Correctamente";
                    break;

                    case "udtPssTrue" : form_update_password_message.innerHTML= `<p class="text-primary">Contraseña actualizada!</p>`;
                    break;

                    case "udtPssFalse" : form_update_password_message.innerHTML=`<p class="text-danger">La contraseña actual no es correcta!!</p>`;
                    break;

                    default: form_update_user_message.innerHTML="";  form_update_password_message.innerHTML="";
                    break;

                }
                

            }

            else{
                window.location("index.html");
            }

        }

    };
    

}

//request api update user
function request_api_update_user(e){

    e.preventDefault();

    let nick= document.getElementById('form-update-user__nick').value;
    let name= document.getElementById('form-update-user__name').value;
    let phone= document.getElementById('form-update-user__phone').value;
    
    let show_email= (document.getElementById('form-update-user__show-email').checked)? true: false;
    let show_phone= (document.getElementById('form-update-user__show-phone').checked)? true: false;
    let show_name= (document.getElementById('form-update-user__show-name').checked)? true: false;

    let id_user=JSON.parse(window.sessionStorage.getItem('flea-market')).id;

    connectionAPi= new XMLHttpRequest();
    connectionAPi.open("PUT","http://3.249.62.209:8080/flea-market/v1/user", true);
    connectionAPi.setRequestHeader('Authorization', `${JSON.parse(window.sessionStorage.getItem('flea-market')).tk}`);      
    connectionAPi.setRequestHeader("Content-Type","application/json");
    connectionAPi.send( JSON.stringify({ idUser:`${id_user}`, nickUser:`${nick}`,  nameUser:`${name}`,  phoneUser:`${phone}`, 
        showNameUser:`${show_name}`, showEmailUser:`${show_email}`, showPhoneUser:`${show_phone}`,
    }));

    connectionAPi.onreadystatechange= ()=>{

        if(connectionAPi.readyState== 4){


            if(connectionAPi.status== 200){


                load_my_account("udtUserTrue"); 
            }
            else{
                alert("Se ha producido un error al actualizar");
                window.location.href='index.html';
            }

        }


    }
    
}

//check form update password
function check_form_update_password(){

    let new_pss= document.getElementById("form-update-password__new-password");
    let new_pss2= document.getElementById("form-update-password__new-password2");
    let check_pss= check_password_user(new_pss.value);

    //Pss
    (check_pss)?new_pss.setCustomValidity("") :
    new_pss.setCustomValidity("Formato de contraseña incorrecto:\n-Debe tener entre 8 y 15 caracteres\n-Alfanumérica\n-Al menos un caracter especial");
        
    //Pss2
    (new_pss2.value== new_pss.value )?new_pss2.setCustomValidity("") :new_pss2.setCustomValidity("Las contraseñas no coinciden");

}

//request api update user
function request_api_update_password(e){

    e.preventDefault();

    let old_pss= document.getElementById("form-update-password__old-password").value;
    let new_pss= document.getElementById("form-update-password__new-password").value;
  
    let id_user=JSON.parse(window.sessionStorage.getItem('flea-market')).id;

    connectionAPi= new XMLHttpRequest();
    connectionAPi.open("PUT","http://3.249.62.209:8080/flea-market/v1/user/password", true);
    connectionAPi.setRequestHeader('Authorization', `${JSON.parse(window.sessionStorage.getItem('flea-market')).tk}`);      
    connectionAPi.setRequestHeader("Content-Type","application/json");
    connectionAPi.send( JSON.stringify({ idUser:`${id_user}`, passwordUser:`${old_pss}`,  newPasswordUser:`${new_pss}`}));

    connectionAPi.onreadystatechange= ()=>{

        if(connectionAPi.readyState== 4){


            if(connectionAPi.status== 200){

                load_my_account("udtPssTrue"); 
            }

            else if(connectionAPi.status== 204){

                load_my_account("udtPssFalse"); 

            }
            else{
                alert("Se ha producido un error al actualizar");
                window.location.href='index.html';
            }

        }


    }
    
}

//request api delete user
function request_api_delete_account(){

    let id_user=JSON.parse(window.sessionStorage.getItem('flea-market')).id;

    connectionAPi= new XMLHttpRequest();
    connectionAPi.open("DELETE", `http://3.249.62.209:8080/flea-market/v1/user/${id_user}`, true);
    connectionAPi.setRequestHeader('Authorization', `${JSON.parse(window.sessionStorage.getItem('flea-market')).tk}`);      
    connectionAPi.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    connectionAPi.send();
    connectionAPi.onreadystatechange= ()=>{

        if(connectionAPi.readyState== 4){

            if(connectionAPi.status== 200){ sign_off(); }

            if(connectionAPi.status== 204){ 
                alert("No se ha encontrado esta cuenta.Por favor intentalo más tarde");
                location.href="index.html";
             }
        
         

        }

    } 

}


//MY-LIKES

//request api my likes
function resquest_api_my_likes(){

    let id_user=JSON.parse(window.sessionStorage.getItem('flea-market')).id;

    connectionAPi= new XMLHttpRequest();
    connectionAPi.open("GET", `http://3.249.62.209:8080/flea-market/v1/ad/list-ad-like-user/${id_user}`, true);
    connectionAPi.send();
    connectionAPi.onreadystatechange = ()=> {

        if(connectionAPi.readyState== 4 && connectionAPi.status == 200){
           
            let result= JSON.parse(connectionAPi.responseText);
               
                if(result['message']== "OK"){
                     search_result= result['data']; 
                    
                     load_result_my_likes();
                    }

                
        }

    }


}

//load result my likes
 function load_result_my_likes(e){

    if(e!=null) e.preventDefault();

    let page= (e== null  || e.target.id<1)?  1 : e.target.id; 

    if(page-1== total_pages)page=total_pages;
 
    let main_home= document.getElementById("main-home");
    main_home.innerHTML="";

    let class_ad_card= "main-index__ad-card row d-flex flex-column align-items-center flex-md-row mb-4 border border-3 border-secondary rounded bg-secondary ";
    let class_card_img="main-index__ad-card__img d-flex justify-content-center col-md  align-content-md-center mt-2 mb-3 my-md-0";
    let class_card_inf="main-index__ad-card__inf w-100  col-md ";
    let class_info_title="main-index__ad-card__inf__title overflow-hidden fs-5 fw-bolder text-lowercase"
    let class_info_priceAndStatus="main-index__ad-card__inf__price-and-status d-flex justify-content-between align-items-center ";
    let class_info_description= "main-index__ad-card__inf__description overflow-hidden fs-6";
    let class_info_localityAndLike="main-index__ad-card__inf__localityAndLike d-flex justify-content-between mt-1 mt-3 align-items-center  ";


    // <i class="fa-solid fa-heart"></i>

   let icono;
    
    for (let i= (page-1)*records_per_page; i< (page * records_per_page); i++) {

     if(i ==search_result.length){break;}

      let ad = search_result[i];
      let refAd= ad['refAd'];
      let user_id_ad= ad['userId'];
      let title= ad['titleAd'];
      let urlMainPhoto= ad['photoList'][0]['urlPhoto'];
      let itemStatus= ad['itemStatusAd'];
      let description= ad['descriptionAd'];
      let price= ad['priceAd'];
      let locality= ad["idLo"];
      
      
      let icono_link= `<a href="#" class="fs-2 link-danger delete-like " data-bs-toggle="modal" data-bs-target="#modal-delete-like" id="${refAd}" ><i class='fa-solid fa-heart'></i></a>`;
     
    
      let div_ad_card= 
        `<div class="${class_ad_card}">
          
            <div class="${class_card_img}">
                                
                 <a href="details.html?refAd=${refAd}" target="_blank" >
                    <img class="align-self-center"  src="${urlMainPhoto}" alt="img-main"></img>
                 </a>

            </div>

            <div class="${class_card_inf}">
                            
                <p class="${class_info_title}">${title}</p>

                <div class="${class_info_priceAndStatus}">
                                    
                    <p class="fs-5  fw-bolder">${price}€</p>
                    <p class="fs-6 text-primary text-uppercase">${itemStatus}</p>
                                    
                </div>

                <div class="${class_info_description}">
                    <p>${description}</p>
                </div>
                                    
                 <div class="${class_info_localityAndLike}">

                     <a class="text-decoration-none" href="https://www.google.es/maps/search/${locality}" target="_blank" >${locality}</a>  
                     ${icono_link}

                 </div>

            </div>
                            
                    
        </div>`;    
    
      main_home.insertAdjacentHTML("beforeend", div_ad_card);
      
    }

    if(search_result.length!=0){
    insert_container_pagination(page, "home.html?section=my-likes");
    }
    add_event_save_refAd();

    document.getElementById('button-delete-like').addEventListener('click', delete_like);

}

//Add event save reference ad
function add_event_save_refAd(){

    let array_like_ad= document.getElementsByClassName('delete-like');

       
    for (let i=0; i< array_like_ad.length; i++) {
      const element = array_like_ad[i];

      element.addEventListener('click',change_refAd);

    }

}


//change ad reference in global variable 
function change_refAd(e){
    e.preventDefault();
    ref_like= e.target.parentNode.id;
}

//delete like
function delete_like(){

    let id_user=JSON.parse(window.sessionStorage.getItem('flea-market')).id;
    connectionAPi= new XMLHttpRequest();
    connectionAPi.open("DELETE", `http://3.249.62.209:8080/flea-market/v1/user/like/id-user/${id_user}/ref-ad/${ref_like}`, true);
    connectionAPi.setRequestHeader('Authorization', `${JSON.parse(window.sessionStorage.getItem('flea-market')).tk}`);      
    connectionAPi.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    connectionAPi.send();
    connectionAPi.onreadystatechange= ()=>{


        if(connectionAPi.readyState==4 && connectionAPi.status == 200){

            let result= JSON.parse(connectionAPi.responseText);

            if(result['code'] == "204 NO_CONTENT"){ 
                ref_like=null;
           
                location.href="home.html?section=my-likes";
            }
        }
    }
}


//NEW-AD

//load content new ad 
function load_content_new_ad(){


    let content=`
    <div class="main-home__new-ad p-2 p-md-0">
    
        <!-- </form> -->

        <form action="home.html?section=new-ad" id="form-new-ad"  enctype='multipart/form-data' class="needs-validation-checkin" > 
            

            <!-- Row -->
            <div class="row" >

                <div class="main-home__new-ad__data-ad col-lg-6 p-1">


                    <!-- Category -->
                    <div class="my-3 bg-secondary p-2 fw-bolder">
                        <label for="exampleFormControlTextarea1" class="form-label">Categoría:</label>
                        <select class="form-select form-select-sm" id="form-new-ad__cat" required>
                            <option>-Selecciona-</option>
                        </select>
                    </div>

                    <!-- Subcategory -->
                    <div class="my-3 bg-secondary p-2 fw-bolder">
                        <label for="exampleFormControlTextarea1" class="form-label">Subcategoría:</label>
                        <select class="form-select form-select-sm"  id="form-new-ad__subCat" required>
                            <option>-Selecciona-</option>
                        </select>
                    </div>
            
                    <!-- ItemSattus -->
                    <div class="my-3  p-2  bg-secondary ">

                        <label for="exampleFormControlTextarea1" class="form-label fw-bolder">Estado Articulo:</label>
                        
                        <div class=" d-flex flex-column align-items-start flex-md-row align-items-md-center">
                            
                            <div class="form-check  ">
                                <input class="form-check-input" type="radio" name="form-new-ad__item-status"  value="nuevo" >
                                <label class="form-check-label" for="itemStatus-nuevo">Nuevo</label>   
                            </div>
                            
                            <div class="form-check  ms-md-4">
                                <input class="form-check-input" type="radio" name="form-new-ad__item-status" value="usado" checked >
                                <label class="form-check-label" for="itemStatus-usado">Usado</label>
                            </div>

                            <div class="form-check  ms-md-4">
                                <input class="form-check-input" type="radio" name="form-new-ad__item-status" value="reparado">
                                <label class="form-check-label" for="itemStatus-reparado">Reparado</label>
                            </div>
                            
                            <div class="form-check ms-md-4">
                                <input class="form-check-input" type="radio" name="form-new-ad__item-status"  value="averiado" >
                                <label class="form-check-label" for="itemStatus-averiado">Averiado</label>
                            </div>
                            
                            
                        </div>
                        
                    </div>

                    <!-- Location -->
                    <div class="my-3 bg-secondary p-2">
                        <label for="form-new-ad__ac" class="form-label fw-bolder">Ubicacion:</label>
                        <!-- AC -->
                        <div class="mb-3">
                            <label for="form-new-ad__ac" class="form-label">Comunida Autónoma:</label>
                            <select class="form-select form-select-sm" id="form-new-ad__ac" required>
                                <option selected>-Selecciona-</option>
                            </select>
                        </div>

                        <!-- Province -->
                        <div class="mb-3" >
                            <label for="form-new-ad__pr" class="form-label">Provincia:</label>
                            <select class="form-select form-select-sm"  id="form-new-ad__pr" required>
                                <option selected>-Selecciona-</option>
                            </select>
                        </div>
                        
                        <!-- Locality -->
                        <div class="mb-3 bg-secondary">
                            <label for="form-new-ad__lo" class="form-label">Localidad:</label>
                            <select class="form-select form-select-sm"  id="form-new-ad__lo" required>
                                <option selected>-Selecciona-</option>
                            </select>
                        </div>       
                        
                    </div>

                    <!-- Price -->
                    <div class="mb-3 p-2  bg-secondary">
                        <label for="form-new-ad__price" class="form-label fw-bolder">Precio:</label>
                        <input type="number" class="form-control " id="form-new-ad__price" step=".001" min="0" max="9999999" aria-label="price" required>
                    </div>

                    <!-- Title -->
                    <div class="mb-3 p-2  bg-secondary">
                        <label for="form-new-ad__title" class="form-label fw-bolder">Título:</label>
                        <input type="text" class="form-control " id="form-new-ad__title" maxlength="50"   aria-label="title" required>
                    </div>

                    <!-- Description -->
                    <div class="mb-3 p-2 bg-secondary" >
                        <label for="form-new-ad__description " class="form-label fw-bolder">Descripción:</label>
                        <textarea class="form-control"  id="form-new-ad__description" maxlength="2000" rows="5" required></textarea>
                    </div>
                

                </div>

                <div class="main-home__new-ad__photos-ad col-lg-6 p-md-2">
                    
                    <p class="title text-primary">Debe subir al menos tres fotos por anuncio.</p>

                    <div class="mb-3">
                        <label for="formFile" class="form-label fw-bolder "><span class="text-danger">*</span>Foto 1:</label>
                        <div class="d-flex">
                            <input class="form-control " type="file"  name="form-new-ad__photo"  id="1" >
                            <button type="button" class="btn btn-danger clear-photo"><i class="far fa-trash-alt "></i></button>
                        </div>
                    </div>

                    
                    <div class="mb-3">
                        <label for="formFile" class="form-label fw-bolder "><span class="text-danger">*</span>Foto 2:</label>
                        <div class="d-flex">
                            <input class="form-control " type="file"  name="form-new-ad__photo" id="2">
                            <button type="button" class="btn btn-danger clear-photo"><i class="far fa-trash-alt "></i></button>
                        </div>
                    </div>


                    <div class="mb-3">
                        <label for="formFile" class="form-label fw-bolder "><span class="text-danger">*</span>Foto 3:</label>
                        <div class="d-flex">
                            <input class="form-control " type="file"  name="form-new-ad__photo" id="3">
                            <button type="button" class="btn btn-danger clear-photo"><i class="far fa-trash-alt "></i></button>
                        </div>
                    </div>


                    <div class="mb-3">
                        <label for="formFile" class="form-label fw-bolder ">Foto 4:</label>
                        <div class="d-flex">
                            <input class="form-control " type="file" name="form-new-ad__photo" id="4">
                            <button type="button" class="btn btn-danger clear-photo"><i class="far fa-trash-alt "></i></button>
                        </div>
                    </div> 

                    <div class="mb-3">
                        <label for="formFile" class="form-label fw-bolder ">Foto 5</label>
                        <div class="d-flex">
                            <input class="form-control " type="file" name="form-new-ad__photo" id="5" >
                            <button type="button" class="btn btn-danger clear-photo"><i class="far fa-trash-alt "></i></button>
                        </div>
                    </div> 

                    <div class="mb-3">
                        <label for="formFile" class="form-label fw-bolder ">Foto 6:</label>
                        <div class="d-flex">
                            <input class="form-control " type="file" name="form-new-ad__photo"  id="6">
                            <button type="button" class="btn btn-danger clear-photo"><i class="far fa-trash-alt "></i></button>
                        </div>
                    </div> 
        

                </div>

            </div>

            <div class="main-home__new-ad__button d-flex justify-content-around my-5 col-lg-6">    
                <button type="submit"  id="form-new-ad__submit" class="btn btn-primary">Publicar</button>
                <button type="reset"  id="form-new-ad__reset" class="btn btn-success">Limpiar</button>
            </div>

        </form>

    </div>`;

   let main_home= document.getElementById('main-home').insertAdjacentHTML('beforeend',content);


   request_api_categories();
   request_api_ac()

   let form_new_ad= document.getElementById('form-new-ad').addEventListener('submit', request_api_new_ad );
   let form_new_ad_submit= document.getElementById('form-new-ad__submit').addEventListener('click',check_form_new_ad);
   let form_new_ad_reset= document.getElementById('form-new-ad__reset').addEventListener('reset',reset_form_new_ad);

   let array_input_photo= document.querySelectorAll('.clear-photo');

   for (let i= 0; i< array_input_photo.length; i++) {
    const buttom = array_input_photo[i];
        buttom.addEventListener('click', reset_photo);   
   }
 
}

//reset photo
function reset_photo(e){
   e.target.parentNode.children[0].value="";
}

//check form new ad
function check_form_new_ad(){

    let cat= document.getElementById('form-new-ad__cat');
    let subcat= document.getElementById('form-new-ad__subCat');
    let ac= document.getElementById('form-new-ad__ac');
    let pr= document.getElementById('form-new-ad__pr');
    let lo= document.getElementById('form-new-ad__lo');

  
    //category
    (!isNaN(cat.value))? cat.setCustomValidity(""): cat.setCustomValidity("Debe seleccionar una categoria.");

    //subCategory
    (!isNaN(subcat.value))? subcat.setCustomValidity(""): subcat.setCustomValidity("Debe seleccionar una subcategoria.");

    //ac
    (!isNaN(ac.value))? ac.setCustomValidity(""): ac.setCustomValidity("Debe seleccionar una comunidad autónoma.");

    //province
    (!isNaN(pr.value))? pr.setCustomValidity(""): pr.setCustomValidity("Debe seleccionar una provincia.");

    //locality
    (!isNaN(lo.value))? lo.setCustomValidity(""): lo.setCustomValidity("Debe seleccionar una localidad.");


}

//reset form new ad
function reset_form_new_ad(e){
    e.reset();

search_filter=null;
cat_filter=null;
subCat_silter=null;
ac_filter=null;
pr_filter=null;
lo_filter=null;
price_min_filter=null;
price_max_filter=null;
status_nuevo_filter=null;
status_usado_filter=null;
status_reparado_filter=null;
status_averiado_filter=null;
order_price_filter=null;

}

//request api new ad
async function request_api_new_ad(e){

    e.preventDefault();

    let subcat= document.getElementById('form-new-ad__subCat').value;
    let lo= document.getElementById('form-new-ad__lo').value;
    let title= document.getElementById('form-new-ad__title').value;
    let price= document.getElementById('form-new-ad__price').value
    let description= document.getElementById('form-new-ad__description').value
    let itemStatus= document.getElementsByName('form-new-ad__item-status');

    for(i = 0; i < itemStatus.length; i++) {
       
        if(itemStatus[i].checked){
            itemStatus=itemStatus[i].value;
              break;
        } 
    }


    let array_photos= document.getElementsByName("form-new-ad__photo");
    let photoList=[];

    for (let i = 0; i< array_photos.length; i++) {
        const photo= array_photos[i];
        
     if(photo.value!=""){
         photoList.push({"urlPhoto" :await convertBase64(photo.files[0])});
     }
        
    }
    let id_user=JSON.parse(window.sessionStorage.getItem('flea-market')).id;

    connectionAPi= new XMLHttpRequest();
    connectionAPi.open("POST", "http://3.249.62.209:8080/flea-market/v1/ad/new-ad", true);
    connectionAPi.setRequestHeader('Authorization', `${JSON.parse(window.sessionStorage.getItem('flea-market')).tk}`);  
    connectionAPi.setRequestHeader("Content-Type","application/json");
    connectionAPi.send( JSON.stringify({ userId:`${id_user}`,  itemStatusAd:`${itemStatus}`, subCatId:`${subcat}`, titleAd:`${title}`, 
    descriptionAd:`${ description}`, priceAd:`${price}`, idLo:`${lo}`, photoList:photoList }));
    
    connectionAPi.onreadystatechange= ()=>{

        if(connectionAPi.readyState== 4){


            if(connectionAPi.status== 200){
                
                window.location.href='home.html?section=my-ads';
            }

            else{
                
              alert("No se ha podido subir el anuncio.Intentelo más tarde");
              window.location.href='home.html?section=new-ad';
            }

        }


    }


} 


// convert to bs64
const convertBase64 = (file) => {
    return new Promise((resolve, reject) => {
        const fileReader = new FileReader();
        fileReader.readAsDataURL(file);

        fileReader.onload = () => {
            resolve(fileReader.result);
        };

        fileReader.onerror = (error) => {
            reject(error);
        };
    });
}

//request api categories
function request_api_categories(){


    let select_category= document.getElementById('form-new-ad__cat');
    select_category.innerHTML= "<option>-Selecciona-</option>";

    connection= new XMLHttpRequest();
    connection.open("GET","http://3.249.62.209:8080/flea-market/v1/cat/list-all-and-sub", true);
    connection.send();
    connection.onreadystatechange= ()=>{

        if(connection.readyState == 4 && connection.status== 200){

            let result= JSON.parse(connection.responseText)['data'];

            list_categories= result;

            let categories="";

            for (let i=0; i< result.length; i++) {
                const cat = result[i];
             
                 categories= categories.concat(`<option value="${cat['idCAt']}">${cat['nameCat']}</option>`);
            }

              select_category.insertAdjacentHTML("beforeend", categories);

              select_category.addEventListener('change', load_select_subcategories)
          
        }

    }

}


//load select subcategories
function load_select_subcategories(){

    let cat_value= document.getElementById('form-new-ad__cat').value;
    let select_subCategory= document.getElementById('form-new-ad__subCat');
    select_subCategory.innerHTML= "<option>-Selecciona-</option>";

    let array_subcategories=[];


    if(cat_value!="Selecciona"){
        
    
        for (let i= 0; i < list_categories.length; i++) {
            const subcat = list_categories[i]['idCAt'];
            
            if(subcat==cat_value){
                array_subcategories= list_categories[i]['subCatList'];
                break;
            }
        }

       
        let subcategories="";
        for (let i= 0; i <  array_subcategories.length; i++) {
            const subcat =  array_subcategories[i];
            subcategories= subcategories.concat(`<option value="${subcat['idSubCat']}">${subcat['nameSubCat']}</option>`)
        }

        select_subCategory.insertAdjacentHTML('beforeend', subcategories);

    }
    
}


//request api ac
function request_api_ac(){

    
    let select_ac= document.getElementById('form-new-ad__ac');
    select_ac.innerHTML= "<option>-Selecciona-</option>";

    connectionAPi= new XMLHttpRequest();
    connectionAPi.open("GET","http://3.249.62.209:8080/flea-market/v1/ac/list-all", true);
    connectionAPi.send();
    connectionAPi.onreadystatechange= ()=>{

        if(connectionAPi.readyState == 4 && connectionAPi.status== 200){

            let result= JSON.parse(connectionAPi.responseText)['data'];

            list_ac= result;

            let autonomous_communities="";

            for (let i=0; i< result.length; i++) {
                const ac= result[i];
             
                autonomous_communities= autonomous_communities.concat(`<option value="${ac['idAc']}">${ac['nameAc']}</option>`);
            }

              select_ac.insertAdjacentHTML("beforeend", autonomous_communities);

              select_ac.addEventListener('change', request_api_pr);
              


        }

    }

}

//request api province
function request_api_pr(){

    
    let ac_value= document.getElementById('form-new-ad__ac').value;
    let select_pr= document.getElementById('form-new-ad__pr');
    select_pr.innerHTML= "<option>-Selecciona-</option>";
    document.getElementById('form-new-ad__pr').innerHTML= "<option>-Selecciona-</option>";

    if(ac_value!="Selecciona"){

        connectionAPi= new XMLHttpRequest();
        connectionAPi.open("GET",`http://3.249.62.209:8080/flea-market/v1/province/list-by-ac/${ac_value}`, true);
        connectionAPi.send();
        connectionAPi.onreadystatechange= ()=>{

            if(connectionAPi.readyState == 4 && connectionAPi.status== 200){

                let result= JSON.parse(connectionAPi.responseText)['data'];

                list_pr= result;

                let provinces="";

                for (let i=0; i< result.length; i++) {
                    const pr= result[i];
                
                    provinces= provinces.concat(`<option value="${pr['idPr']}">${pr['namePr']}</option>`);
                }

                    select_pr.insertAdjacentHTML("beforeend", provinces);

                    select_pr.addEventListener('change', request_api_lo);
            
            }

        }

    }

    

}


//request api province
function request_api_lo(){

    
    let pr_value= document.getElementById('form-new-ad__pr').value;
    let select_lo= document.getElementById('form-new-ad__lo');
    select_lo.innerHTML= "<option>-Selecciona-</option>";

    if(pr_value!="Selecciona"){

        connectionAPi= new XMLHttpRequest();
        connectionAPi.open("GET",`http://3.249.62.209:8080/flea-market/v1/locality/list-by-pr/${pr_value}`, true);
        connectionAPi.send();
        connectionAPi.onreadystatechange= ()=>{

            if(connectionAPi.readyState == 4 && connectionAPi.status== 200){

                let result= JSON.parse(connectionAPi.responseText)['data'];

                let localities="";

                for (let i=0; i< result.length; i++) {
                    const lo= result[i];
                
                    localities= localities.concat(`<option value="${lo['idLo']}">${lo['nameLo']}</option>`);
                }

                    select_lo.insertAdjacentHTML("beforeend", localities);


            
            }

        }

    }

    

}






//MY-ADS

//request api my ads
function resquest_ap_my_ads(){

    let id_user=JSON.parse(window.sessionStorage.getItem('flea-market')).id;

    connectionAPi= new XMLHttpRequest();
    connectionAPi.open("GET", `http://3.249.62.209:8080/flea-market/v1/ad/list-ad-user/${id_user}`, true);
    connectionAPi.send();
    connectionAPi.onreadystatechange = ()=> {

        if(connectionAPi.readyState== 4 && connectionAPi.status == 200){
           
            let result= JSON.parse(connectionAPi.responseText);
               
                if(result['message']== "OK"){ search_result= result['data']; }

                load_result_my_ads();
        }

    }
 
}

//load result my likes
function load_result_my_ads(e){

   
    if(e!=null) e.preventDefault();

    let page= (e== null  || e.target.id<1)?  1 : e.target.id; 

    if(page-1== total_pages)page=total_pages;
 
    let main_home= document.getElementById("main-home");
    main_home.innerHTML="";

    let class_ad_card= "main-index__ad-card row d-flex flex-column align-items-center flex-md-row mb-4 border border-3 border-secondary rounded bg-secondary ";
    let class_card_img="main-index__ad-card__img d-flex justify-content-center col-md  align-content-md-center mt-2 mb-3 my-md-0";
    let class_card_inf="main-index__ad-card__inf w-100  col-md ";
    let class_info_title="main-index__ad-card__inf__title overflow-hidden fs-5 fw-bolder text-lowercase"
    let class_info_priceAndStatus="main-index__ad-card__inf__price-and-status d-flex justify-content-between align-items-center ";
    let class_info_description= "main-index__ad-card__inf__description overflow-hidden fs-6";
    let class_info_localityAndLike="main-index__ad-card__inf__localityAndLike d-flex justify-content-between mt-1 mt-3 align-items-center ";


    
    for (let i= (page-1)*records_per_page; i< (page * records_per_page); i++) {

     if(i ==search_result.length){break;}


      let ad = search_result[i];
      let refAd= ad['refAd'];
      let user_id_ad= ad['userId'];
      let title= ad['titleAd'];
      let urlMainPhoto= ad['photoList'][0]['urlPhoto'];
      let itemStatus= ad['itemStatusAd'];
      let description= ad['descriptionAd'];
      let price= ad['priceAd'];
      let locality= ad["idLo"];
      
      
      let icono_link= `<a href="#" class="fs-6 link-danger delete-ad text-decoration-none fw-bolder" data-bs-toggle="modal" data-bs-target="#modal-delete-ad" id="${refAd}" >Eliminar</a>`;
     
    
      let div_ad_card= 
        `<div class="${class_ad_card}">
          
            <div class="${class_card_img}">
                                
                 <a href="details.html?refAd=${refAd}" target="_blank" >
                    <img class="align-self-center"  src="${urlMainPhoto}" alt="img-main"></img>
                 </a>

            </div>

            <div class="${class_card_inf}">
                            
                <p class="${class_info_title}">${title}</p>

                <div class="${class_info_priceAndStatus}">
                                    
                    <p class="fs-5  fw-bolder">${price}€</p>
                    <p class="fs-6 text-primary text-uppercase">${itemStatus}</p>
                                    
                </div>

                <div class="${class_info_description}">
                    <p>${description}</p>
                </div>
                                    
                 <div class="${class_info_localityAndLike}">

                     <a class="text-decoration-none" href="https://www.google.es/maps/search/${locality}" target="_blank" >${locality}</a>  
                     ${icono_link}

                 </div>

            </div>
                            
                    
        </div>`;    
    
      main_home.insertAdjacentHTML("beforeend", div_ad_card);
      
    }

    if(search_result.length!=0){
    insert_container_pagination(page, "home.html?section=my-ads");
    }
    add_event_save_ref_Ad("delete-ad");

    document.getElementById('button-delete-ad').addEventListener('click', delete_ad);

}


//Add event save reference ad
function add_event_save_ref_Ad(){

    let array_like_ad= document.getElementsByClassName('delete-ad');

       
    for (let i=0; i< array_like_ad.length; i++) {
      const element = array_like_ad[i];
      element.addEventListener('click',change_ref_Ad);

    }

}


//change ad reference in global variable 
function change_ref_Ad(e){
    e.preventDefault();
    ref_like= e.target.id;
}

//delete ad
function delete_ad(){

    connectionAPi= new XMLHttpRequest();
    connectionAPi.open("DELETE", `http://3.249.62.209:8080/flea-market/v1/ad/${ref_like}`, true);
    connectionAPi.setRequestHeader('Authorization', `${JSON.parse(window.sessionStorage.getItem('flea-market')).tk}`);      
    connectionAPi.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    connectionAPi.send();
    connectionAPi.onreadystatechange= ()=>{


        if(connectionAPi.readyState==4 && connectionAPi.status == 200){

            let result= JSON.parse(connectionAPi.responseText);

            if(result['code'] == "204 NO_CONTENT"){ 
                ref_like=null; 
                location.href="home.html?section=my-ads";
            }
        }
    }
}



//***** ABORT REQUEST API ******
function abort_request(){
        connectionAPi.abort();
        let main_index= document.getElementById("main-index");
        main_index.innerHTML="";
        main_index.insertAdjacentHTML( "afterbegin","<p class='text-center text-primary fs-5'>Lo sentimos, el servidor esta saturado</p>");
}


