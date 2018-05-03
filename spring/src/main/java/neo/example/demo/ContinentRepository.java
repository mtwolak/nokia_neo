package neo.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ContinentRepository extends JpaRepository<Continent, String> {

}
