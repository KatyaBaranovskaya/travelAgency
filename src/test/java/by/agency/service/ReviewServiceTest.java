package by.agency.service;

import by.agency.domain.Review;
import by.agency.service.impl.ReviewServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReviewServiceTest {
    private ReviewServiceImpl reviewService;

    private Review review;

    @Before
    public void init(){
        reviewService = mock(ReviewServiceImpl.class);
        review = new Review();
        review.setId(1);
        review.setContent("5*");
    }

    @After
    public void remove() {
        review = null;
    }

    @Test
    public void save() {
        when(reviewService.add(review)).thenReturn(true);
        reviewService.add(review);
        verify(reviewService).add(review);
    }

    @Test
    public void readTest() {
        when(reviewService.getEntityById(review.getId())).thenReturn(review);
        reviewService.getEntityById(review.getId());
        verify(reviewService).getEntityById(review.getId());
    }

    @Test
    public void readAllTest() {
        reviewService.getAll();
        verify(reviewService).getAll();
    }

    @Test
    public void deleteTest() {
        when(reviewService.removeById(review.getId())).thenReturn(true);
        reviewService.removeById(review.getId());
        verify(reviewService).removeById(review.getId());
    }

    @Test
    public void update() {
        when(reviewService.update(review)).thenReturn(true);
        reviewService.update(review);
        verify(reviewService).update(review);
    }
}
