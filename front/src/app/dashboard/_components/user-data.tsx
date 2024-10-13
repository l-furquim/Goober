'use client'

import ExitAccount from "@/app/view/user/[user]/_components/exit-account";
import { Button } from "@/components/ui/button";
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuLabel, DropdownMenuSeparator, DropdownMenuTrigger } from "@/components/ui/dropdown-menu";
import { UserCircleIcon, UserIcon, WalletIcon } from "lucide-react"
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
      
      <DropdownMenu>
          <DropdownMenuTrigger className="flex flex-row gap-2">Lucas<UserCircleIcon className="mt-[0.5px]"/> </DropdownMenuTrigger>
      
            <DropdownMenuContent>
              <DropdownMenuLabel>Minha conta</DropdownMenuLabel>
                <DropdownMenuSeparator />
                  <Link href={`/view/user/${value}`} className="justify-center flex">
                    <DropdownMenuItem className="bg-zinc-300 text-black hover:bg-zinc-400 hover:cursor-pointer w-[86%] h-full rounded-md justify-center font-semibold">
                      <UserIcon size={15}/> Perfil
                      </DropdownMenuItem>
                  </Link>
                  <DropdownMenuItem className="font-semibold">
                    <ExitAccount/>
                  </DropdownMenuItem>
             </DropdownMenuContent>
        </DropdownMenu>

      </div>
    
    </div>
    )
    
}