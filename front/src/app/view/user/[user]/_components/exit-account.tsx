import { Button } from "@/components/ui/button";
import { ExitIcon } from "@radix-ui/react-icons";
import { deleteCookie, getCookie } from "cookies-next";
import { WalletIcon } from "lucide-react";

const ExitAccount = () => {
    
    const exit = () => {
        deleteCookie("goober-auth");
        location.reload();
    }

    return (
        <Button onClick={exit} className="bg-zinc-300 text-black hover:bg-zinc-400 hover:cursor-pointer w-full">
            <ExitIcon/> Sair
        </Button>
    )

}

export default ExitAccount;