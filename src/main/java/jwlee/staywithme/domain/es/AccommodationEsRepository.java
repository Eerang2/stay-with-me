package jwlee.staywithme.domain.es;

import jwlee.staywithme.domain.es.documents.AccommodationDoc;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationEsRepository extends ElasticsearchRepository<AccommodationDoc, String> {

    @Query("{\"wildcard\": {\"name\": {\"value\": \"*?0*\"}}}")
    List<AccommodationDoc> findByNameLike(String name);
}
