'use client'

import { Button } from "@/components/ui/button";
import { UserCircleIcon } from "lucide-react"
import Link from "next/link"
import { useRouter } from "next/navigation";
import qs from "qs";
import React from "react";
import { useState } from "react";

export const UserData = () => {

    const router = useRouter();

    const value = "Lucas";

    const [showOptions,setShowOptions] = useState(false);


    const onSubmit = (e: React.FormEvent<HTMLFormElement>) => {

        e.preventDefault();

        if(!value) return;

        const query = qs.stringify({ term: value });

        const url = `/dashboard/home/profile?${query}`;
        router.push(url);
    };

    const onClickUser = () => {
        setShowOptions(!showOptions);
    }


    return (
    <div>
    
      <div onClick={onClickUser} className="flex flex-row gap-2 hover:opacity-75 transition hover:cursor-pointer">
      Lucas<UserCircleIcon className="mt-[0.5px]"/> 

      </div>
    
      {showOptions ? (

        <div className="absolute flex flex-col items-center justify-center gap-3 p-14 rounded-xl bg-zinc-300 w-full h-full">
          <div>
            <Link href={`/view/user/${value}`}>
              <Button>Ver perfil</Button>
            </Link>
          </div>
          <div>
            <Link href={""}>
              <Button>Sair</Button>
            </Link>
          </div>
        </div>

      ): (
        <></>
      )}

    </div>
    )
    
}