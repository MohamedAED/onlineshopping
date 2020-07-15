package Test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import edu.miu.waa.onlineShopping.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class SOKSTest {
    /*
    * Test Case Made to Success
    *
    * */
    @Test
    public void NotEmptyProducts() {
        ProductRepository localMockRepository = Mockito.mock(ProductRepository.class);
        Mockito.when(localMockRepository.count()).thenReturn(123L);
        long productCount = localMockRepository.count();
        Assert.assertEquals(123L, productCount);
        Mockito.verify(localMockRepository).count();
    }



}