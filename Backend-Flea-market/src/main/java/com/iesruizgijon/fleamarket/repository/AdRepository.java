package com.iesruizgijon.fleamarket.repository;

import com.iesruizgijon.fleamarket.model.Ad;

import com.iesruizgijon.fleamarket.model.Filter;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Set;


/**
 * AD REPOSITORY
 */

@Repository
public interface AdRepository extends JpaRepository<Ad,Integer > {
   // Set<Ad> findByUserId_IdUser(Integer idUser);

    Set<Ad> findByUserId_IdUserOrderByLastUpdateAdDesc(Integer idUser);


    Set<Ad> findByLikeUserList_IdUserOrderByLastUpdateAdDesc(Integer idUser);

    Set<Ad> findBySubCatId_IdSubCatOrderByLastUpdateAdDesc(Integer idSubCat);

    Set<Ad> findBySubCatId_CatId_IdCAtOrderByLastUpdateAdDesc(Integer idCAt);

    /*@Query(value = "SELECT ad FROM Ad ad" +
            " WHERE " +
            " (:#{#filter.search} IS NULL OR (UPPER(ad.titleAd) like %:#{#filter.search}% OR UPPER(ad.descriptionAd) like %:#{#filter.search}%) )" +
            " AND (:#{#filter.cat} IS NULL OR ad.subCatId.catId.idCAt = :#{#filter.cat}) " +
            " AND (:#{#filter.subcat} IS NULL OR ad.subCatId.idSubCat = :#{#filter.cat})" +
            " AND (:#{#filter.ac} IS NULL OR ad.idLo.prId.acId.idAc = :#{#filter.ac})" +
            " AND (:#{#filter.pr} IS NULL OR ad.idLo.prId.idPr = :#{#filter.pr})" +
            " AND (:#{#filter.lo} IS NULL OR ad.idLo.idLo = :#{#filter.lo})" +
            " AND (
 IS NULL OR ad.priceAd >= :#{#filter.min_price})" +
            " AND (:#{#filter.max_price} IS NULL OR ad.priceAd <= :#{#filter.max_price})" +
            " ORDER BY ad.priceAd ASC, ad.lastUpdateAd DESC "
    )
    Set<Ad> findAdByFilterService(Filter filter);*/

    @Query(value = "select ad.* from ad  " +
            " join sub_category_ad sca on ad.sub_cat_id = sca.id_sub_cat  " +
            " join category_ad ca on sca.cat_id = ca.id_cat  " +
            " join locality l on ad.id_lo = l.id_lo  " +
            " join province p on l.pr_id = p.id_pr  " +
            " join ac a on p.ac_id = a.id_ac  " +
            " where  " +
            " (:#{#filter.search} is null OR :#{#filter.search} = '' OR ad.title_ad like %:#{#filter.search}% OR ad.description_ad like %:#{#filter.search}%) " +
            " AND (:#{#filter.subcat} = -1 OR sca.id_sub_cat = :#{#filter.subcat}) " +
            " AND (:#{#filter.cat} = -1 OR ca.id_cat = :#{#filter.cat}) " +
            " AND (:#{#filter.lo} = -1 OR l.id_lo = :#{#filter.lo}) " +
            " AND (:#{#filter.pr} = -1 or p.id_pr = :#{#filter.pr} ) " +
            " AND (:#{#filter.ac} = -1 or a.id_ac = :#{#filter.ac}) " +
            " AND (:#{#filter.min_price} = -1 or ad.price_ad >= :#{#filter.min_price}) " +
            " AND (:#{#filter.max_price} = -1 or ad.price_ad <= :#{#filter.max_price}) " +
            " AND (ad.item_status_ad IN :#{#filter.statusList}) " +
            " ORDER BY ad.price_ad ASC , ad.last_update_ad DESC;" , nativeQuery = true
    )
    Set<Ad> findAdByFilterServiceOrderPriceASC(Filter filter);

    @Query(value =  "select ad.* from ad  " +
            " join sub_category_ad sca on ad.sub_cat_id = sca.id_sub_cat  " +
            " join category_ad ca on sca.cat_id = ca.id_cat  " +
            " join locality l on ad.id_lo = l.id_lo  " +
            " join province p on l.pr_id = p.id_pr  " +
            " join ac a on p.ac_id = a.id_ac  " +
            " where  " +
            " (:#{#filter.search} is null OR :#{#filter.search} = '' OR ad.title_ad like %:#{#filter.search}% OR ad.description_ad like %:#{#filter.search}%) " +
            " AND (:#{#filter.subcat} = -1 OR sca.id_sub_cat = :#{#filter.subcat}) " +
            " AND (:#{#filter.cat} = -1 OR ca.id_cat = :#{#filter.cat}) " +
            " AND (:#{#filter.lo} = -1 OR l.id_lo = :#{#filter.lo}) " +
            " AND (:#{#filter.pr} = -1 or p.id_pr = :#{#filter.pr} ) " +
            " AND (:#{#filter.ac} = -1 or a.id_ac = :#{#filter.ac}) " +
            " AND (:#{#filter.min_price} = -1 or ad.price_ad >= :#{#filter.min_price}) " +
            " AND (:#{#filter.max_price} = -1 or ad.price_ad <= :#{#filter.max_price}) " +
            " AND (ad.item_status_ad IN :#{#filter.statusList}) " +
            " ORDER BY ad.price_ad DESC , ad.last_update_ad DESC;"  , nativeQuery = true
    )
    Set<Ad> findAdByFilterServiceOrderPriceDESC(Filter filter);



/*
    @Query("select filter from Filter filter"

            +"where"
            + " (:#{#filter.search} IS NULL OR ( UPPER(ad.titleAd) LIKE UPPER(:#{#filter.search}) ) OR
                UPPER(ad.descriptionAd) LIKE UPPER(:#{#filter.search})) )"
            + "AND (:#{#filter.evulProject} IS NULL OR evulAnalytic.evulProject.id = :#{#filter.evulProject}) "


    )




*/




}
