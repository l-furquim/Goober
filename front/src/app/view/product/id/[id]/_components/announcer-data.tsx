import type React from "react";
import type { AnnouncementType } from "../page";
import { HandshakeIcon, HeartIcon, MapPin, ShoppingCartIcon, Tags } from "lucide-react";
import { Button } from "@/components/ui/button";
import AddCart from "./add-cart";

const AnnouncerData: React.FC<AnnouncementType> = ({announcement}) => {

  return (
    <div className="bg-zinc-600 flex-col  flex abolute items-center justify-start 
             absolute right-20 ml-8 mt-10 h-[450px] rounded-xl max-w-full w-2/5 gap-10 ">
                 
                 <p className="text-4xl flex flex-row items-center justify-center gap-5 mt-5 text-zinc-300">
                     R$ {announcement.announcementPrice.toString()} <Tags className="text-green-600" size={"35px"}/>
                 </p>
                 <p className="text-2xl text-zinc-300 font-bold"> {announcement.announcementName}</p>
                 <div className="space-y-5 bg-zinc-900 h-40 w-80  items-center justify-normal flex flex-col rounded-xl">
                     <p className="mt-5  text-zinc-300 ">{announcement.announcerName}</p>
                     <hr className="border-1 w-32"/>
                     <p className=" text-zinc-300 flex flex-row  text-sm gap-2"><MapPin size={"20px"}/> {
                     `${announcement.announcementStreet}, ${announcement.announcementNumber}-${announcement.announcementState}` 
                     }</p>
                     {/* <p className="text-xs mb-10 text-zinc-300 text-muted-foreground">
                        qweoijeiowje
                     </p> */}
                 </div>    
             
                 <div className="flex flex-row items-center justify-center gap-5">
                     <Button className="bg-zinc-300 text-black  gap-3 mt-auto hover:bg-zinc-400">
                         Comprar agora <HandshakeIcon size={"18px"} className="mt-1"/>
                     </Button>
         
                     <AddCart announcement={announcement}/>
         
                     <HeartIcon/>
                 </div>
             </div>
                
  )

}


export default AnnouncerData