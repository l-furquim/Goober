'use client'

import { NavBar } from "@/app/dashboard/_components/nav-bar";
import { Button } from "@/components/ui/button";
import { HandshakeIcon, HeartIcon, MapPin, ShoppingCartIcon, Tags } from "lucide-react";
import Image from "next/image";
import { useSearchParams } from "next/navigation";
import { useState } from "react";



export default  function ProductContainer ({params}: {params: {product: string};}) {

    const searchParams = useSearchParams();

    const name = searchParams.get("name");
    const category = searchParams.get('category');
    const price = searchParams.get("price");

    const imagesUrl = ['foto morangao.png', 'mouseGamer.webp', 'fotocachorrao.jpg'];

    const [imageSelected, setImageSelected] = useState(imagesUrl.at(0));

    const [selected, setSelected] = useState(false);

    const descriptions = ['Morangao bala ultra 4k com 4 portas ultra air bag muita coisa maneira compra ai otario ' + 
        'EOJWEOQJEWQIOJEWQOIEJQWOIEJQEOIQWJEOIWQJoiQJ'
    ];

    const categories = ['GAMER', 'LIMPEZA'];

    const questions = ['faz parcelado em 3 anos?', 'manda no peito do pai', 'odiei'];
    

    const handleSwith = () => {
        setSelected(!selected);
    }

    return (
    <>
    <NavBar/>
        <div className="container bg-zinc-900 ml-11 mt-10 h-[2000px] items-center justify-center">
    
            <div className="bg-zinc-600 flex-col flex abolute items-center justify-start 
            absolute right-14 ml-8 mt-10 h-[450px] rounded-xl max-w-full w-2/5 gap-10 ">
                
                <p className="text-4xl flex flex-row items-center justify-center gap-3 mt-5 text-zinc-300">
                    R$ {price} <Tags className="text-zinc-900" size={"35px"}/>
                </p>
                <p className="text-xl text-zinc-300"> {name}</p>
                <div className="container space-y-5 bg-zinc-900 h-40 w-80  items-center justify-normal flex flex-col rounded-xl">
                    <p className="mt-5  text-zinc-300 ">{"Lucas Hernandes Furquim"}</p>
                    <hr className="border-1 w-32"/>
                    <p className=" text-zinc-300 flex flex-row  text-sm"><MapPin size={"20px"}/>{"Endereço"}</p>
                    <p className="text-xs mb-10 text-zinc-300 text-muted-foreground">{"Rua silvia 1603"}</p>
                </div>    
            
                <div className="flex flex-row items-center justify-center gap-5 ">
                    <Button className="bg-zinc-900  gap-3 mt-auto">
                        Comprar agora <HandshakeIcon size={"18px"} className="mt-1"/>
                    </Button>

                    <Button className="gap-2">
                        Adicionar ao Carrinho <ShoppingCartIcon size={"18px"}/>
                    </Button>

                    <HeartIcon/>
                </div>
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


                        
                {descriptions.map((desc) => (
                <>      
                        <div className=" flex-col containerh-[300px] flex items-center justify-start 
                        absolute left-40 ml-8 mt-40 p-10 rounded-xl max-w-full w-2/6  ">
                                <div className="flex flex-row gap-8">{categories.map((cat) => (
                                    <p className="container rounded-sm mt-5 bg-teal-950 text-zinc-300 ">{cat}</p>
                                
                                ))}
                                </div>
                                
                                <p className="flex flex-row  left-20 mt-10 text-zinc-300 text-xl">{desc}</p>
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
                ))}

        </div>
        </>
    );

};
