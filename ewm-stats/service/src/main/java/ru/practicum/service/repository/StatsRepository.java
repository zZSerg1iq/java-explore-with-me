package ru.practicum.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.service.model.Stats;
import ru.practicum.service.model.ResultEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsRepository extends JpaRepository<Stats, Long> {

    @Query("SELECT s.app as app, s.uri as uri , s.ip as ip " +
            "FROM Stats s " +
            "WHERE  s.timestamp BETWEEN :start AND :end " +
            "GROUP BY s.ip, s.app, s.uri")
    List<ResultEntity> findAllByDate_UniqueIp(@Param("start") LocalDateTime start,
                                              @Param("end") LocalDateTime end);

@Query(nativeQuery = true, value =
        "SELECT app, uri, ip " +
        "FROM stats s " +
        "WHERE s.uri IN :uris " +
        "AND s.timestamp BETWEEN :start AND :end "
        +"group by s.ip, s.app, s.uri")
    List<ResultEntity> findAllByUriAndDate_UniqueIp(@Param("start") LocalDateTime start,
                                                    @Param("end") LocalDateTime end,
                                                    @Param("uris") List<String> uris);

    @Query("SELECT s.app as app, s.uri as uri " +
            "FROM Stats s " +
            "WHERE s.timestamp BETWEEN :start AND :end ")
    List<ResultEntity> findAllByDate(@Param("start") LocalDateTime start,
                                     @Param("end") LocalDateTime end);

    @Query("SELECT s.app as app, s.uri as uri " +
            "FROM Stats s " +
            "WHERE s.uri IN :uris " +
            "AND s.timestamp BETWEEN :start AND :end ")
    List<ResultEntity> findAllByDateAndUri(@Param("start") LocalDateTime start,
                                           @Param("end") LocalDateTime end,
                                           @Param("uris") List<String> uris);
}
