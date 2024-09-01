'use client'

import { ArrowDown } from "lucide-react"
import { useState } from "react"


export const FilterBar = () => {

    const [isHoveredCategories, setIsHoveredCategories] = useState(false);
    const [isHoveredPrice, setIsHoveredPrice] = useState(false);


    return (
    <>
        <div className="relative flex flex-row ml-5 mr-5 bg-zinc-900 rounded-b-xl h-20">
            <p onMouseEnter={() => setIsHoveredCategories(true)} onMouseLeave={() =>  setIsHoveredCategories(false)} 
            className="text-sm mt-auto text-zinc-300 flex-row flex items-center justify-center text-center w-full gap-1 cursor-pointer
            ">Categorias<ArrowDown size={"15px"}/></p>
            <p onMouseEnter={() => setIsHoveredPrice(true)} onMouseLeave={() => setIsHoveredPrice(false)} 
            className="text-sm text-zinc-300 mt-auto flex-row flex items-center justify-center text-center w-full gap-1 cursor-pointer 
            ">Preço<ArrowDown size={"15px"}/></p>
        </div>
        <div>


        {isHoveredCategories && (
                
            <nav onMouseEnter={() => setIsHoveredCategories(true)} onMouseLeave={() =>  setIsHoveredCategories(false)} 
            className="mt-auto flex-row flex ml-72 bg-zinc-600 w-fit rounded-b-xl text-center gap-1 z-10" >
               <ul className="p-2"> 
                        <li className="py-1 px-3 hover:bg-zinc-400">Gamer</li>
                        <li className="py-1 px-3 hover:bg-zinc-400">Bem estar</li>
                        <li className="py-1 px-3 hover:bg-zinc-400">Coco</li>
                </ul>
            </nav>
            )}
        {isHoveredPrice && (

            <nav onMouseEnter={() => setIsHoveredCategories(true)} onMouseLeave={() =>  setIsHoveredCategories(false)} 
            className="absolute right-0 mr-72 bg-zinc-600 w-fit rounded-b-xl text-center gap-1 z-10" >
            <ul className="p-2"> 
                    <li className="py-1 px-3 hover:bg-zinc-400">R$ 100,00</li>
                    <li className="py-1 px-3 hover:bg-zinc-400">R$ 200,00</li>
                    <li className="py-1 px-3 hover:bg-zinc-400">R$ 300,00</li>
            </ul>
            </nav>
        )}

        </div>
    
    </>
    )

}