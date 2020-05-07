package com.apirest.app.Evento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//----------------------------Controlador para manejar peticiones HTTP--------------------------

@Controller // This means that this class is a Controller
@RequestMapping(path="/evento") // This means URL's start with /Eventos (after Application path)
public class EventoController {
	  @Autowired // This means to get the bean called EventosRepository
      // Which is auto-generated by Spring, we will use it to handle the data
	  public EventoRepository EventoRepository;
	  
	  // CODIGO PARA PETICIONES POST
	  @PostMapping(path="/add") // Map ONLY POST Requests
	  public @ResponseBody String addNewEvento (
			  @RequestParam String nombre,
			  @RequestParam String descripcion,
			  @RequestParam String url_img,
			  @RequestParam Long id_ubicacion
			  ) {
	    // @ResponseBody means the returned String is the response, not a view name
	    // @RequestParam means it is a parameter from the GET or POST request
		  
		 Evento n = new Evento();
		  n.setNombre(nombre);
		  n.setDescripcion(descripcion);
		  n.setUrl_img(url_img);
		  n.setId_ubicacion(id_ubicacion);
		  EventoRepository.save(n);
		  return "Guardado";
	  } 
	  
	  @GetMapping(path="/all")
	  public @ResponseBody Iterable<Evento> getAllEventos() {
		  //This returns a JSON or XML with the Eventos
		  return EventoRepository.findAll();
	  }
	  
	  //Ejemplos para POST y GET. Por defecto RequestMapping mapea todas las operaciones HTTP
}