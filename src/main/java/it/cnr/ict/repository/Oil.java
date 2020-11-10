package it.cnr.ict.repository;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import it.cnr.ict.domain.Category;
import it.cnr.ict.domain.ExternalProblem;
import it.cnr.ict.domain.User;

import java.io.File;
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
    Long addAttachment(@Param("id")Long id,  @Param("file")File file, @Param("instance")String instance);

    // CATEGORY

    @RequestLine("POST /catg/{instance}")
    List<Category> getCategories(@Param("instance")String instance);

    @RequestLine("PUT /catg/{instance}")
    void addCategory(Category category, @Param("instance")String instance);

    @RequestLine("POST /catg/{instance}")
    void modifyCategory(Category category, @Param("instance")String instance);

    @RequestLine("DELETE /catg/{instance}/{id}")
    void deleteCategory(@Param("instance")String instance, @Param("id")String id);

    // USER

    @RequestLine("GET /user/{instance}")
    List<User> getUsers(@Param("instance")String instance);

    @RequestLine("PUT /user/{instance}")
    void addUser(User user, @Param("instance")String instance);

    @RequestLine("POST /user/{instance}")
    void modifyUser(User user, @Param("instance")String instance);

    @RequestLine("DELETE /user/{instance}/{id}")
    void deleteUser(@Param("instance")String instance, @Param("id")String id);

    // EXPERT

    @RequestLine("GET /ucat/{instance}/{id}")
    List<User> getExperts(@Param("instance")String instance, @Param("id")String id);

    @RequestLine("GET /ucat/{instance}/{uid}")
    List<User> getExpertCategories(@Param("instance")String instance, @Param("uid")String uid);

    @RequestLine("PUT /ucat/{instance}/{id}/{uid}")
    void assignCategory2User(@Param("instance")String instance, @Param("id")String id, @Param("uid")String uid);

    @RequestLine("POST /ucat/{instance}/{id}/{uid}")
    void assignCategory2UserBIS(@Param("instance")String instance, @Param("id")String id, @Param("uid")String uid);

    @RequestLine("DELETE /ucat/{instance}/{id}/{uid}")
    void removeCategory2User(@Param("instance")String instance, @Param("id")String id, @Param("uid")String uid);

}
