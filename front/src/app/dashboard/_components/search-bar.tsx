'use client'

import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { SearchIcon } from "lucide-react"
import { useRouter } from "next/navigation";
import qs from "qs";
import { useState } from "react";


export const SearchBar = () => {

    const router = useRouter();

    const [value, setValue] = useState("");

    const onSubmit = (e: React.FormEvent<HTMLFormElement>) => {

        e.preventDefault();

        if(!value) return;

        const query = qs.stringify({ term: value });

        const url = `/dashboard/home/search?${query}`;
        router.push(url);
    };



    return (
        <form className="relative w-[400px] flex items-center gap-5" onSubmit={onSubmit}>

            <Input className="bg-zinc-950 border border-zinc-400 border-muted-foreground focus-visible:ring-transparent" placeholder="Buscar" value={value}
            onChange={(e) => setValue(e.target.value)}/>

            <Button className="bg-zinc-300 text-black hover:opacity-75 transition " variant={"secondary"} type="submit">
                <SearchIcon className="text-muted-foreground h-5 w-5"/>
            </Button>

        </form>
    )

}