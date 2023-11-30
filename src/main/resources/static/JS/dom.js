const d=document,
    $table=d.querySelector(".crud-table")
    $form=d.querySelector(".crud-form")
    $title=d.querySelector(".crud-title")
    $template=d.getElementById("crud-template").content,
    $fragment=d.createDocumentFragment()

    const getTodosTickets= async()=>{
        try {
           let res=await fetch("http://localHost:8080/api/TicketControl/verTodos"),
           json=await res.json();
               console.log(json)
            json.forEach(el=>{
                $template.querySelector(".id_ticket").textContent=el.id_Ticket;
                $template.querySelector(".transportista").textContent=el.transportista.nombre_Transp +" "+el.transportista.apellido_Transp;
                 $template.querySelector(".generador").textContent=el.generador.nombre_generador; 
                 $template.querySelector(".fecha").textContent=el.fechaEmisionTk; 
                 if(el.estadoTicket===true){
                    $template.querySelector(".estado-ticket").textContent="Activo"; 
                 }else{
                    $template.querySelector(".estado-ticket").textContent="Inactivo"; 
                 }


                $template.querySelector(".edit").dataset.id=el.id;
                $template.querySelector(".edit").dataset.name=el.nombre;
                $template.querySelector(".edit").dataset.constellation=el.constelación;

                $template.querySelector(".delete").dataset.id=el.id;
                $template.querySelector(".delete").dataset.name=el.nombre;
                $template.querySelector(".delete").dataset.constellation=el.constelación;


                let $clone= d.importNode($template,true);
                $fragment.appendChild($clone);
            })
            $table.querySelector("tbody").appendChild($fragment)

    const storedScrollPositionX = localStorage.getItem("scrollPositionX");
    const storedScrollPositionY = localStorage.getItem("scrollPositionY");

    if (storedScrollPositionX !== null && storedScrollPositionY !== null) {
      window.scrollTo(storedScrollPositionX, storedScrollPositionY);
    } else {
      window.scrollTo(0, 0);
    }
           if(!res.ok){
            throw{
                status: res.status, statusText: res.statusText
            };
           }
        

        
    }catch (err) {
            let message= err.statusText || "Ocurrió un error";
            $table.insertAdjacentHTML("afterend",`<p><b>Error${err.status}:${message}</b> </p> `)
        }
    }


    d.addEventListener("DOMContentLoaded",getTodosTickets);