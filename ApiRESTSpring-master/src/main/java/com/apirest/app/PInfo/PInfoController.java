package com.apirest.app.PInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//----------------------------Controlador para manejar peticiones HTTP--------------------------

@Controller // This means that this class is a Controller
@RequestMapping(path="/pinfo") // This means URL's start with /PInfos (after Application path)
public class PInfoController {
	  @Autowired // This means to get the bean called PInfosRepository
      // Which is auto-generated by Spring, we will use it to handle the data
	  public PInfoRepository PInfoRepository;
	  
	  // CODIGO PARA PETICIONES POST
	  @PostMapping(path="/add") // Map ONLY POST Requests
	  public @ResponseBody String addNewPInfo (
			  @RequestParam String nombre,
			  @RequestParam String descripcion,
			  @RequestParam String url_img,
			  @RequestParam Long id_ubicacion
			  ) {
	    // @ResponseBody means the returned String is the response, not a view name
	    // @RequestParam means it is a parameter from the GET or POST request
		  
		 PInfo n = new PInfo();
		  n.setNombre(nombre);
		  n.setDescripcion(descripcion);
		  n.setUrl_img(url_img);
		  n.setId_ubicacion(id_ubicacion);
		  PInfoRepository.save(n);
		  return "Guardado";
	  } 
	  
	  @GetMapping(path="/all")
	  public @ResponseBody Iterable<PInfo> getAllPInfos() {
		  //This returns a JSON or XML with the PInfos
		  return PInfoRepository.findAll();
	  }
	  
	  //Ejemplos para POST y GET. Por defecto RequestMapping mapea todas las operaciones HTTP
}