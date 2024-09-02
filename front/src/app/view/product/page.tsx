'use client'

import { MapPin } from "lucide-react";
import Image from "next/image";
import { useSearchParams } from "next/navigation";
import { useState } from "react";



export default  function ProductContainer ({params}: {params: {product: string};}) {

    const searchParams = useSearchParams();

    const name = searchParams.get("name");
    const category = searchParams.get('category');
    const price = searchParams.get("price");

    const imagesUrl = ['foto morangao.png', 'mouseGamer.webp'];

    const [imageSelected, setImageSelected] = useState(imagesUrl.at(0));

    const [selected, setSelected] = useState(false);

    const handleSwith = () => {
        setSelected(!selected);
    }

    return (
        <div className="container bg-zinc-400 ml-11 mt-10 h-[600px] items-center justify-center">

            <div className="bg-zinc-600 flex-col flex abolute items-center justify-start 
            absolute right-14 ml-8 mt-10 h-96 rounded-xl max-w-full w-2/5 gap-10 ">
                
                <p className="text-4xl mt-5 text-zinc-300">R$ {price}</p>
                <p className="text-xl text-zinc-300"> {name}</p>
                <p className="absolute bottom-0 mb-3 right-0 mr-3 text-teal-950">{category}</p>
                <p className="container space-y-5 bg-zinc-900 h-40 w-80  items-center justify-normal flex flex-col rounded-xl">
                    <h1 className="mt-5  text-zinc-300 ">{"Lucas Hernandes Furquim"}</h1>
                    <p className=" text-zinc-300     flex flex-row  text-sm"><MapPin size={"20px"}/>{"Endereço"}</p>
                    <p className="text-xs mb-10 text-zinc-300 text-muted-foreground">{"Rua silvia 1603"}</p>
                </p>    
            
            </div>

            <div className="flex flex-row items-center">
              <div className="flex flex-col space-y-3"> 
              {imagesUrl.map((image) => (
                    <p onClick={() => {setImageSelected(image); handleSwith();} } 
                    className="w-16 flex-col flex h-16 ml-3 bg-black mr-4 border border-solid border-black">
                    
                    <Image alt={`imagem-${image}`} width={"54"} height={"54"}
                    src={`/images/${image}`} style={{borderColor: selected ? 'purple' : 'black', borderRadius: selected? 3: 0,
                      width: '100%', height: '100%',  }}/>
                    </p>
              
              ))}
              </div>
            
                 <Image
                    alt="morangasso"
                    src={`/images/${imageSelected}`}
                    width={600}
                    height={300}
                    style={{ transform: 'scale(0.7)', backgroundColor: 'rgba(255, 255, 255, 0)' }}/>
            </div>


        </div>
    );

};
