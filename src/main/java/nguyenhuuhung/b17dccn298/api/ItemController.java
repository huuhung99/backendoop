package nguyenhuuhung.b17dccn298.api;

import nguyenhuuhung.b17dccn298.api.response.ResponseBodyDto;
import nguyenhuuhung.b17dccn298.api.response.ResponseCodeEnum;
import nguyenhuuhung.b17dccn298.model.Item;
import nguyenhuuhung.b17dccn298.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<ResponseBodyDto<List<Item>>> getAll(){
        List<Item> items = itemService.getAll();
        ResponseBodyDto<List<Item>> response=new ResponseBodyDto(items, ResponseCodeEnum.R_200,"OK",items.size());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping
    public ResponseEntity<ResponseBodyDto<Item>> createOrUpdate(@RequestBody Item request){
        Item item = itemService.createOrUpdate(request);
        ResponseBodyDto<Item> response=new ResponseBodyDto<>(item,ResponseCodeEnum.R_201,"Created");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBodyDto<Item>> delete(@PathVariable Long id){
        ResponseBodyDto<Item> response=new ResponseBodyDto<>();
        if(itemService.deleteById(id)){
            response.setMessage("Xóa thành công!");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        response.setMessage("id không tồn tại.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
