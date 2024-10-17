'use client'

import ExitAccount from "@/app/dashboard/_components/exit-account";
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuLabel, DropdownMenuSeparator, DropdownMenuTrigger } from "@/components/ui/dropdown-menu";
import { UserCircleIcon} from "lucide-react"
import { useRouter } from "next/navigation";
import qs from "qs";
import React from "react";
import { useState } from "react";
import SeeProfile from "./see-profile";

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
                
                    <DropdownMenuItem>
                      <SeeProfile userName={value}/>
                    </DropdownMenuItem>
                  <DropdownMenuItem className="font-semibold">
                    <ExitAccount/>
                  </DropdownMenuItem>
             </DropdownMenuContent>
        </DropdownMenu>

      </div>
    
    </div>
    )
    
}