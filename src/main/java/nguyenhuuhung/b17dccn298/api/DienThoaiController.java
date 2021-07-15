package nguyenhuuhung.b17dccn298.api;

import nguyenhuuhung.b17dccn298.api.response.ResponseCodeEnum;
import nguyenhuuhung.b17dccn298.model.DienThoai;
import nguyenhuuhung.b17dccn298.service.DienThoaiService;
import nguyenhuuhung.b17dccn298.api.response.ResponseBodyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dienthoai")
public class DienThoaiController {
    @Autowired
    private DienThoaiService dienThoaiService;

    @GetMapping
    public ResponseEntity<ResponseBodyDto<List<DienThoai>>> getAll(){
        List<DienThoai> dienThoais = dienThoaiService.getAll();
        ResponseBodyDto<List<DienThoai>> response=new ResponseBodyDto(dienThoais, ResponseCodeEnum.R_200,"OK",dienThoais.size());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/search/{keyword}")
    public ResponseEntity<ResponseBodyDto<List<DienThoai>>> findAllByName(@PathVariable String keyword){
        List<DienThoai> dienThoais = dienThoaiService.search(keyword);
        ResponseBodyDto<List<DienThoai>> response=new ResponseBodyDto(dienThoais, ResponseCodeEnum.R_200,"OK",dienThoais.size());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseBodyDto<List<DienThoai>>> getById(@PathVariable Long id){
        List<DienThoai> dienThoais = dienThoaiService.getById(id);
        ResponseBodyDto<List<DienThoai>> response=new ResponseBodyDto(dienThoais, ResponseCodeEnum.R_200,"OK",dienThoais.size());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping
    public ResponseEntity<ResponseBodyDto<DienThoai>> createOrUpdate(@RequestBody DienThoai request){
        DienThoai dienThoai = dienThoaiService.createOrUpdate(request);
        ResponseBodyDto<DienThoai> response=new ResponseBodyDto<>(dienThoai,ResponseCodeEnum.R_201,"Created");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBodyDto<DienThoai>> delete(@PathVariable Long id){
        ResponseBodyDto<DienThoai> response=new ResponseBodyDto<>();
        if(dienThoaiService.deleteById(id)){
            response.setMessage("Xóa thành công!");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        response.setMessage("id không tồn tại.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
