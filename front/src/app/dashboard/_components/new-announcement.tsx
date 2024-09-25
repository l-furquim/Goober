'use client'


import { Button } from "@/components/ui/button";
import { getCookie } from "cookies-next";
import { PlusCircleIcon } from "lucide-react";
import { useRouter } from "next/navigation";

const NewAnnouncementButton = () => {

    const router = useRouter();

    const handleNewAnnouncement = () => {

        const userid = getCookie("goober-auth");


        router.push(`/announcement/new/${userid}`);

    }

    return (

        <div className="rounded-xl">
                <Button onClick={handleNewAnnouncement} className="bg-zinc-300 text-black hover:bg-zinc-400 gap-2 p-3"> 
                    <PlusCircleIcon size={"15px"}/>
                    Anunciar
                </Button>
        </div>
    )

}

export default NewAnnouncementButton;