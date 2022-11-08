package com.raspberry.awards.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.raspberry.awards.entities.Producer;
import com.raspberry.awards.entities.ProducerAwards;

public interface ProducerRepository extends JpaRepository<Producer, Long>{

	Optional<Producer> findByName(String name);
	
	@Query(value = 
			  "with awards as (\r\n"
			+ "select x.*, rank() over (order by x.range asc) rank\r\n"
			+ "from (\r\n"
			+ "	select p.name \r\n"
			+ "		 , lag(m.movie_year, 1, m.movie_year) over (partition by p.id order by m.movie_year desc) - m.movie_year as range\r\n"
			+ "		 , m.movie_year previousWin\r\n"
			+ "		 , lag(m.movie_year, 1, m.movie_year) over (partition by p.id order by m.movie_year desc) as followingWin\r\n"
			+ "	  from producer p\r\n"
			+ "	 inner join movie_producer mp\r\n"
			+ "		on mp.producer_id = p.id \r\n"
			+ "	 inner join movie m\r\n"
			+ "		on m.id = mp.movie_id\r\n"
			+ "	 where m.winner = true\r\n"
			+ "	 group by p.name \r\n"
			+ "			, m.movie_year\r\n"
			+ "	 order by p.name) x\r\n"
			+ "where range <> 0\r\n"
			+ "group by x.name,\r\n"
			+ "		    x.previousWin,\r\n"
			+ "		    x.followingWin,\r\n"
			+ "		    x.range\r\n"
			+ ") \r\n"
			+ "select * from awards where rank = 1",
		   nativeQuery = true)
	List<ProducerAwards> getBestWinners();
	
	@Query(value = 
			  "with awards as (\r\n"
			+ "select x.*, rank() over (order by x.range desc) rank\r\n"
			+ "from (\r\n"
			+ "	select p.name \r\n"
			+ "		 , lag(m.movie_year, 1, m.movie_year) over (partition by p.id order by m.movie_year desc) - m.movie_year as range\r\n"
			+ "		 , m.movie_year previousWin\r\n"
			+ "		 , lag(m.movie_year, 1, m.movie_year) over (partition by p.id order by m.movie_year desc) as followingWin\r\n"
			+ "	  from producer p\r\n"
			+ "	 inner join movie_producer mp\r\n"
			+ "		on mp.producer_id = p.id \r\n"
			+ "	 inner join movie m\r\n"
			+ "		on m.id = mp.movie_id\r\n"
			+ "	 where m.winner = true\r\n"
			+ "	 group by p.name \r\n"
			+ "			, m.movie_year\r\n"
			+ "	 order by p.name) x\r\n"
			+ "where range <> 0\r\n"
			+ "group by x.name,\r\n"
			+ "		    x.previousWin,\r\n"
			+ "		    x.followingWin,\r\n"
			+ "		    x.range\r\n"
			+ ") \r\n"
			+ "select * from awards where rank = 1",
		   nativeQuery = true)
	List<ProducerAwards> getWorstWinners();
}
