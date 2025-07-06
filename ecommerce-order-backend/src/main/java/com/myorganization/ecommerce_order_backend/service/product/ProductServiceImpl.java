package com.myorganization.ecommerce_order_backend.service.product;

import com.myorganization.ecommerce_order_backend.Exception.ProductNotFound;
import com.myorganization.ecommerce_order_backend.dto.request.ProductRequestDto;
import com.myorganization.ecommerce_order_backend.dto.response.ProductResponseDto;
import com.myorganization.ecommerce_order_backend.model.Product;
import com.myorganization.ecommerce_order_backend.model.UserCart;
import com.myorganization.ecommerce_order_backend.repository.ProductRepo;
import com.myorganization.ecommerce_order_backend.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    UserRepo userRepo;
    public List<Product> pl =new ArrayList<>();

    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        UserCart uc =new UserCart();

       Product p= convertDtoTOProduct(productRequestDto,new Product());

       pl.add(p);
       uc.setProducts(pl);
      p = productRepo.save(p);
      return covertProductToDto(p);
    }

    @Override
    public String removeProduct(Long prdId) {
        Product p = productRepo.findById(prdId).orElse(null);
    if(p!=null){
        productRepo.delete(p);
        return "product with id =  "+"   no Longer exists in database";
    }
        throw new ProductNotFound("product not Present in data base");


    }

    @Override
    public ProductResponseDto UpdateProduct(Long prdId, ProductRequestDto productRequestDto) {



       Product p = productRepo.findById(prdId).orElse(null);
       if(p!=null){
            p= convertDtoTOProduct(productRequestDto,p);
          p = productRepo.save(p);
            return covertProductToDto(p);
       }
       throw new ProductNotFound("Product not may be DELETED  or  NOT_CREATED   =  "+prdId);


    }


    @Override

    public Page<ProductResponseDto> getProductsName( String sortBy, String grpby, Integer size, Integer pageno) {
        Sort ss =grpby.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pg = PageRequest.of(pageno,size,ss);
        Page<Product> pl = productRepo.findAll(pg);
        Page<ProductResponseDto> Dto = pl.map(p->covertProductToDto(p));
        return Dto;


    }

    @Override
    public ProductResponseDto getProductId(Long id) {
        Product p = productRepo.findById(id).orElse(null);
        if(p!=null){
           return covertProductToDto(p);
        }
        throw new ProductNotFound("Product not may be DELETED  or  NOT_CREATED   =  "+id);

    }

    @Override
    public List<ProductResponseDto> getProductsName(String name) {
        List<Product> pr =productRepo.findByProductName(name);
        List<ProductResponseDto> pc =new ArrayList<>();
        for (Product p :pr){
            ProductResponseDto pt =new ProductResponseDto();
            pt=covertProductToDto(p);
            pc.add(pt);
        }
        return pc.stream().sorted(Comparator.comparingLong(ProductResponseDto::getPrdId)).collect(Collectors.toList());

    }



    //helper Method to convert ProductDto to Product
    Product convertDtoTOProduct(ProductRequestDto productRequestDto , Product p){
      try{  p.setProductName(productRequestDto.getProductName());
        p.setProductQuantity(productRequestDto.getProductQuantity());
        p.setProductSeller(productRequestDto.getProductSeller());
        p.setPricefor1(productRequestDto.getPriceFor1());
        p.setPrdDescription(productRequestDto.getPrdDescription());
        p.setProductSeller(productRequestDto.getSellerEmail());
        }
      catch (Exception e){
          log.error("error in conversion of method",e);
      }
      return p;
    }
    //Helper to convert product to ProductDto
    ProductResponseDto covertProductToDto(Product pr){
        return ProductResponseDto.builder().
                prdId(pr.getPrdId()).
                productSeller(pr.getProductSeller())
                .productQuantity(pr.getProductQuantity())
                .pricefor1(pr.getPricefor1())
                .productName(pr.getProductName())
                .prdDescription(pr.getPrdDescription())
                .build();

    }
}
