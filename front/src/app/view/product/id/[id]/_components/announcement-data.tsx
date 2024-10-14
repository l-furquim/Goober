import type { AnnouncementType } from "../page";
import Image from "next/image";


const AnnouncementData: React.FC<AnnouncementType> = ({announcement}) => {
  const questions = ["Faz o L"]

  return (
    <>
    <div className="flex flex-row items-center">
                     <Image alt="morangasso"
                     src={
                      `http://localhost:8080/announcement/get/images/src/main/resources/static/images/announcement/${encodeURIComponent(announcement.announcementName.toString())}`}
                     
                                width={600}
                     height={300}
                     style={{ transform: 'scale(0.7)', backgroundColor: 'rgba(255, 255, 255, 0)' }}/>
             </div>

                   
                         <div className=" flex-col containerh-[300px] flex items-center justify-start 
                         absolute left-40 ml-8 mt-40 p-10 rounded-xl max-w-full w-2/6  ">
                                 <div className="flex flex-row gap-8">
                                     <p className="container rounded-sm mt-5 bg-teal-950 text-zinc-300 ">{announcement.products.map(prod => (
                                        prod.productCategorie
                                     ))}</p>
                                 </div>
                                 
                                 <p className="flex flex-row  left-20 mt-10 text-zinc-300 text-xl">{announcement.products.map(prod => (
                                    prod.productDescription
                                 ))}</p>
                                 <hr className="border-1 mt-10 w-[600px]"></hr>
                                 
                                 <div className="flex mt-30 p-10 flex-col justify-center items-center gap-20 ">
                                         <h1 className="text-2xl font-bold text-zinc-400">Perguntas e Respostas:</h1>
                                             {questions.map((q) => (
                                                 <div className="container space-y-5 bg-white p-10 w-[600px] h-[170px] rounded-xl">
                                                     <p className="text-zinc-400">{"Lucas Furquim"}</p>
                                                     <p>{q}</p>
                                                 </div>
                                             ))}
                                 </div>
                                       
                         </div> 
      </>
  )
}

export default AnnouncementData;