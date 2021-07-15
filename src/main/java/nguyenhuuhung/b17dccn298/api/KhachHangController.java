package nguyenhuuhung.b17dccn298.api;

import nguyenhuuhung.b17dccn298.api.response.ResponseBodyDto;
import nguyenhuuhung.b17dccn298.api.response.ResponseCodeEnum;
import nguyenhuuhung.b17dccn298.model.KhachHang;
import nguyenhuuhung.b17dccn298.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/khachhang")
public class KhachHangController {
    @Autowired
    private KhachHangService khachHangService;
    @GetMapping
    public ResponseEntity<ResponseBodyDto<List<KhachHang>>> getAll(){
        List<KhachHang> KhachHangs = khachHangService.getAll();
        ResponseBodyDto<List<KhachHang>> response=new ResponseBodyDto(KhachHangs, ResponseCodeEnum.R_200,"OK",KhachHangs.size());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping
    public ResponseEntity<ResponseBodyDto<KhachHang>> createOrUpdate(@RequestBody KhachHang request){
        KhachHang khachHang = khachHangService.createOrUpdate(request);
        ResponseBodyDto<KhachHang> response=new ResponseBodyDto<>(khachHang,ResponseCodeEnum.R_201,"Created");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBodyDto<KhachHang>> delete(@PathVariable Long id){
        ResponseBodyDto<KhachHang> response=new ResponseBodyDto<>();
        if(khachHangService.deleteById(id)){
            response.setMessage("Xóa thành công!");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        response.setMessage("id không tồn tại.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
