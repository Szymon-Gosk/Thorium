package thorium.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thorium.web.models.Render;

import java.math.BigInteger;

@Repository
public interface RenderRepository extends JpaRepository<Render, BigInteger> {



}
