package testing;

import dao.QuestionsDao;
import org.springframework.stereotype.Service;


@Service
public class TestingServiceImpl implements TestingService {

    private final QuestionsDao dao;

    public TestingServiceImpl(QuestionsDao dao) {
        this.dao = dao;
    }
}
