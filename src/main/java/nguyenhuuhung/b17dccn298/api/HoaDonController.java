package nguyenhuuhung.b17dccn298.api;
import nguyenhuuhung.b17dccn298.api.response.ResponseBodyDto;
import nguyenhuuhung.b17dccn298.api.response.ResponseCodeEnum;
import nguyenhuuhung.b17dccn298.model.HoaDon;
import nguyenhuuhung.b17dccn298.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hoadon")
public class HoaDonController {

    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping
    public ResponseEntity<ResponseBodyDto<List<HoaDon>>> getAll(){
        List<HoaDon> HoaDons = hoaDonService.getAll();
        ResponseBodyDto<List<HoaDon>> response=new ResponseBodyDto(HoaDons, ResponseCodeEnum.R_200,"OK",HoaDons.size());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping
    public ResponseEntity<ResponseBodyDto<HoaDon>> createOrUpdate(@RequestBody HoaDon request){
        HoaDon hoaDon = hoaDonService.createOrUpdate(request);
        ResponseBodyDto<HoaDon> response=new ResponseBodyDto<>(hoaDon,ResponseCodeEnum.R_201,"Created");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBodyDto<HoaDon>> delete(@PathVariable Long id){
        ResponseBodyDto<HoaDon> response=new ResponseBodyDto<>();
        if(hoaDonService.deleteByid(id)){
            response.setMessage("Xóa thành công!");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        response.setMessage("id không tồn tại.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    
}
