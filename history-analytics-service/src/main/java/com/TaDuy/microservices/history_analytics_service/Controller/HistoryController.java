package com.TaDuy.microservices.history_analytics_service.Controller;


import com.TaDuy.microservices.history_analytics_service.DTO.HistoryDTO;
import com.TaDuy.microservices.history_analytics_service.Service.Imp.HistoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/history")
public class HistoryController {
    @Autowired
    HistoryServiceImp historyServiceImp;

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getHistoryByUserId(@PathVariable  Integer userId){
        List<HistoryDTO> historyDTOList  =historyServiceImp.getHistoryByUserId(userId);

        return new ResponseEntity<>(historyDTOList ,HttpStatus.OK);
    };


    @GetMapping("/get")
    public ResponseEntity<?> getAllHistory(){
        historyServiceImp.getAllHistory();
        return new ResponseEntity<>(historyServiceImp,HttpStatus.OK);
    }

    public ResponseEntity<?> getPromptFromHistory(){

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
