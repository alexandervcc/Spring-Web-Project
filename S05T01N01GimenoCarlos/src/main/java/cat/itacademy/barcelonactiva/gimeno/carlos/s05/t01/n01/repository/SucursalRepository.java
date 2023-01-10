package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.model.domain.Sucursal;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {
	
	//JPQL sin Query: palabras reservadas
	Sucursal findByNombreSucursal(String nombreSucursal);
	
	//JPQL con Query:
	@Query(value = "SELECT * FROM Sucursal s WHERE s.nombreSucursal = :nombreSucursal")
	Sucursal findByNombreSucursalJPQL(String nombreSucursal);
	
	//Native Query
	@Query(value = "SELECT * FROM tbl_sucursal s WHERE s.nombre = :nombreSucursal", nativeQuery = true)
	Sucursal findByNombreSucursalNative(String nombreSucursal);
}
