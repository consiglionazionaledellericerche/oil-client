package it.cnr.ict.repository;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.form.FormData;
import it.cnr.ict.domain.Category;
import it.cnr.ict.domain.ExternalProblem;
import it.cnr.ict.domain.User;

import java.util.List;

@Headers({"Content-Type: application/json"})
public interface Oil {

    // EXTERNAL_PROBLEM

    @RequestLine("PUT /pest/{instance}")
    Long newProblem(ExternalProblem problem, @Param("instance")String instance);

    @RequestLine("POST /pest/{instance}")
    Long addField(ExternalProblem problem, @Param("instance")String instance);

    @RequestLine("POST /pest/{instance}/{id}")
    @Headers("Content-Type: multipart/form-data")
    Long addAttachment(@Param("id")Long id, @Param("allegato")FormData formData, @Param("instance")String instance);

    // CATEGORY

    @RequestLine("POST /catg/{instance}")
    List<Category> getCategories(@Param("instance")String instance);

    @RequestLine("PUT /catg/{instance}")
    Long addCategory(Category category, @Param("instance")String instance);

    @RequestLine("POST /catg/{instance}")
    void modifyCategory(Category category, @Param("instance")String instance);

    @RequestLine("DELETE /catg/{instance}/{id}")
    void deleteCategory(@Param("id")String id, @Param("instance")String instance);

    // USER

    @RequestLine("GET /user/{instance}")
    List<User> getUsers(@Param("instance")String instance);

    @RequestLine("PUT /user/{instance}")
    void addUser(User user, @Param("instance")String instance);

    @RequestLine("POST /user/{instance}")
    void modifyUser(User user, @Param("instance")String instance);

    @RequestLine("DELETE /user/{instance}/{id}")
    void deleteUser(@Param("id")String id, @Param("instance")String instance);

    // EXPERT

    @RequestLine("GET /ucat/{instance}/{categoryId}")
    List<User> getExperts(@Param("categoryId")Long categoryId, @Param("instance")String instance);

    @RequestLine("GET /ucat/{instance}/{uid}")
    List<User> getExpertCategories(@Param("uid")String uid, @Param("instance")String instance);

    @RequestLine("PUT /ucat/{instance}/{id}/{uid}")
    void assignCategory2User(@Param("id")String id, @Param("uid")String uid, @Param("instance")String instance);

    @RequestLine("POST /ucat/{instance}/{id}/{uid}")
    void assignCategory2UserBIS(@Param("id")String id, @Param("uid")String uid, @Param("instance")String instance);

    @RequestLine("DELETE /ucat/{instance}/{id}/{uid}")
    void removeCategory2User(@Param("id")String id, @Param("uid")String uid, @Param("instance")String instance);

}
