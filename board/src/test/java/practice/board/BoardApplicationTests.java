package practice.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import practice.board.config.JpaConfig;
import practice.board.repository.ArticleRepository;


@SpringBootTest
@Import(JpaConfig.class)
@DisplayName("Main")
class BoardApplicationTests {


	@Test
	void context() {

	}

}
