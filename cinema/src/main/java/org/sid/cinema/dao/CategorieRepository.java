package org.sid.cinema.dao;

import org.sid.cinema.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
/* en utilisant Spring data REST @RepositoryRestResource
 c'est à dire  toutes les methodes herités du JpaRepository , elles sont accessibles
 via un API REST*/
@RepositoryRestResource
@CrossOrigin("*")
public interface CategorieRepository extends JpaRepository<Categorie, Long>{

}
