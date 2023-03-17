package com.iesruizgijon.fleamarket.security.service.implement;


import com.iesruizgijon.fleamarket.exceptions.FleaMarketException;
import com.iesruizgijon.fleamarket.exceptions.NoContentExeption;
import com.iesruizgijon.fleamarket.exceptions.NotFoundException;
import com.iesruizgijon.fleamarket.model.*;
import com.iesruizgijon.fleamarket.model.dto.AdDto;
import com.iesruizgijon.fleamarket.model.dto.AdDtoRest;
import com.iesruizgijon.fleamarket.model.dto.PhotoDto;
import com.iesruizgijon.fleamarket.model.dto.PhotoDtoRest;
import com.iesruizgijon.fleamarket.model.emun.ItemStatus;
import com.iesruizgijon.fleamarket.repository.AdRepository;
import com.iesruizgijon.fleamarket.repository.PhotoRepository;
import com.iesruizgijon.fleamarket.repository.UserRepository;
import com.iesruizgijon.fleamarket.security.service.AdService;
import com.iesruizgijon.fleamarket.util.constans.ExceptionConstants;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.*;


/*AD SERVICE IMPLEMENT*/

@Service
public class AdServiceImplement implements AdService {


    @Autowired
    AdRepository adRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    PhotoRepository photoRepo;


     private ModelMapper modelMapper= new ModelMapper();

    /*Create ad by AdDto*/
    @Override
    public void createAdService(AdDto adDto) throws FleaMarketException {

        Ad ad = createNewAd(adDto);

        ad.setPhotoList(createNewPhotoList(adDto, ad));

        adRepo.save(ad);

    }

    /*Delete AD by refAd*/
    @Override
    public void deleteAdService(Integer refAd)throws FleaMarketException {

        Ad ad= adRepo.findById(refAd).orElseThrow(() ->new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_AD));
        adRepo.delete(ad);

    }

    /* Update ad*/
    @Override
    @Transactional
    public void upAdService(AdDto adDto)throws FleaMarketException {

        Ad ad= adRepo.findById(adDto.getRefAd()).orElseThrow(() -> new NoContentExeption(ExceptionConstants.MESSAGE_INEXISTENT_AD));

        photoRepo.deleteByAdRef(ad);

        ad.setItemStatusAd(adDto.getItemStatusAd());
        ad.setSubCatId( new SubCategoryAd(adDto.getSubCatId()));
        ad.setTitleAd(adDto.getTitleAd());
        ad.setDescriptionAd(adDto.getDescriptionAd());
        ad.setPriceAd(adDto.getPriceAd());
        ad.setIdLo(new Locality(adDto.getIdLo()));
        ad.setPhotoList(createNewPhotoList(adDto, ad));

        adRepo.save(ad);

    }


    /* List all ad*/
    @Override
    public List<AdDtoRest> findAllAdService() throws FleaMarketException {

        List<Ad> listAd= adRepo.findAll();

        return createListAdDtoRest(listAd);


    }


   /* Return list ad´s from user */
    public List<AdDtoRest> findAdFromUserService(Integer idUser) throws FleaMarketException{;

        Set<Ad> listAd= adRepo.findByUserId_IdUserOrderByLastUpdateAdDesc(idUser);

        return createSetAdDtoRest(listAd);

    }


    /* Return list user´s likes */
    @Override
    public List<AdDtoRest>  findLikeAdFromUserService(Integer idUser)  throws FleaMarketException{

        Set<Ad> listAd= adRepo.findByLikeUserList_IdUserOrderByLastUpdateAdDesc(idUser);
        return createSetAdDtoRest(listAd);

    }


   /* Return list ad by Subcategory */
    public List<AdDtoRest>  findAdBySubcategoryService(Integer idSubCat) throws FleaMarketException{

        Set<Ad> listAd= adRepo.findBySubCatId_IdSubCatOrderByLastUpdateAdDesc(idSubCat);
        return createSetAdDtoRest(listAd);

    }

   /* Return list ad by Category */
    public List<AdDtoRest>  findAdByCategoryService(Integer idCate) throws FleaMarketException{

        Set<Ad> listAd= adRepo.findBySubCatId_CatId_IdCAtOrderByLastUpdateAdDesc(idCate);
        return createSetAdDtoRest(listAd);
    }

    /*Return list ad by filter*/
    @Override
    public List<AdDtoRest> findAdByFilterService(Filter filter) throws FleaMarketException {





        Set<Ad> listAd= new HashSet<>();
        filter.setStatusList(createListStatus(filter));

        System.out.println(filter.getSearch());
        System.out.println(filter.getCat());
        System.out.println(filter.getSubcat());
        System.out.println(filter.getAc());
        System.out.println(filter.getPr());
        System.out.println(filter.getLo());
        System.out.println(filter.getMin_price());
        System.out.println(filter.getMax_price());
        System.out.println(filter.getStatus_nuevo());
        System.out.println(filter.getStatus_usado());
        System.out.println(filter.getStatus_reparado());
        System.out.println(filter.getStatus_averiado());
        System.out.println(filter.getOrder_price());
        System.out.println(filter.getStatusList());

        if(filter.getOrder_price().equals("ASC")){

            listAd= adRepo.findAdByFilterServiceOrderPriceASC(filter);
        } else {

            listAd= adRepo.findAdByFilterServiceOrderPriceDESC(filter);
        }


        System.out.println("longitud "+ listAd.size());
        return createSetAdDtoRest(listAd);
    }


    /*Return list ad by refAd*/
    @Override
    public AdDtoRest findAdByRefAdService(Integer refAd) throws FleaMarketException {


        Ad ad=  adRepo.findById(refAd).orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_AD));

        return createNewAdDtoRest(ad);


    }


    //********CONVERT ******************************************************

    //Create List AdDtoRest
    private List<AdDtoRest> createListAdDtoRest( List<Ad> listAd ){

        List<AdDtoRest> adDtoRestList = new ArrayList<>();

        for (Ad ad: listAd) {

            AdDtoRest adDtoRest = createNewAdDtoRest(ad);
            adDtoRestList.add(adDtoRest);
        }

        return adDtoRestList;

    }

    //Create List AdDtoRest
    private List<AdDtoRest> createSetAdDtoRest( Set<Ad> listAd){

        List<AdDtoRest> adDtoRestList = new ArrayList<>();

        for (Ad ad: listAd) {

            AdDtoRest adDtoRest = createNewAdDtoRest(ad);
            adDtoRestList.add(adDtoRest);
        }

        return adDtoRestList;

    }


    //AdDto to Ad Converter (Builder Create)

    private Ad createNewAd(AdDto adDto){

        Ad ad= new Ad();
        Optional <User> user= userRepo.findById(adDto.getUserId());

        if(user.isPresent()) {

            ad.setUserId(user.get());
            ad.setItemStatusAd(adDto.getItemStatusAd());
            ad.setSubCatId( new SubCategoryAd(adDto.getSubCatId()));
            ad.setTitleAd(adDto.getTitleAd());
            ad.setDescriptionAd(adDto.getDescriptionAd());
            ad.setPriceAd(adDto.getPriceAd());
            ad.setIdLo(new Locality(adDto.getIdLo()));

        }

        Ad newAd=  adRepo.save(ad);

        return newAd;
    }



    //AD to AdDtoReturn
    private AdDtoRest createNewAdDtoRest(Ad ad){

        AdDtoRest adDtoRest = new AdDtoRest();

        adDtoRest.setRefAd(ad.getRefAd());
        adDtoRest.setUserId(ad.getUserId().getIdUser());
        adDtoRest.setItemStatusAd(ad.getItemStatusAd());
        adDtoRest.setSubCatId(ad.getSubCatId().getNameSubCat());
        adDtoRest.setTitleAd(ad.getTitleAd());
        adDtoRest.setDescriptionAd(ad.getDescriptionAd());
        adDtoRest.setPriceAd(ad.getPriceAd());
        adDtoRest.setIdLo(ad.getIdLo().getNameLo());
        adDtoRest.setLastUpdateAd(ad.getLastUpdateAd());
        adDtoRest.setNumLikes(ad.getLikeUserList().size());
        adDtoRest.setPhotoList(createNewPhotoDtoRestList(ad));

        return adDtoRest;

    }


    //PhotoList to PhotoDtoReturn
    private List<PhotoDtoRest> createNewPhotoDtoRestList(Ad ad ){

        List<Photo> photoList = ad.getPhotoList();
        List<PhotoDtoRest> photoDtoRestList = new ArrayList<>();

        for (Photo photo: photoList) {

            PhotoDtoRest PhotoDtoRest = new PhotoDtoRest(photo.getCodPhoto(), photo.getUrlPhoto());

            photoDtoRestList.add(PhotoDtoRest);
        }

        return photoDtoRestList;

    }



    //PhotoDto to PhotoList
    private List<Photo> createNewPhotoList(AdDto adDto, Ad ad){

        List<PhotoDto> photoDtoList = adDto.getPhotoList();
        List<Photo> photoList= new ArrayList<>();

        for (PhotoDto photoDto: photoDtoList) {

            Photo Photo= new Photo(ad,photoDto.getUrlPhoto());

            photoList.add(Photo);
        }

        return photoList;

    }

    private List<Integer> createListStatus(Filter filter){
        List<Integer> list= new ArrayList<>();
        if(filter.getStatus_nuevo()== true){
            list.add(ItemStatus.nuevo.ordinal());
        }
        if(filter.getStatus_usado()){
            list.add(ItemStatus.usado.ordinal());
        }
        if(filter.getStatus_reparado()){
            list.add(ItemStatus.reparado.ordinal());
        }
        if(filter.getStatus_averiado()){
            list.add(ItemStatus.averiado.ordinal());
        }
        if(list.isEmpty()){
            list.add(ItemStatus.nuevo.ordinal());
            list.add(ItemStatus.usado.ordinal());
            list.add(ItemStatus.reparado.ordinal());
            list.add(ItemStatus.averiado.ordinal());
        }

        System.out.println("lista "+ list);
        return list;
    }


}
