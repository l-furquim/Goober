import { ThemeButton } from "@/components/theme/theme-button";
import { GitHubLogoIcon, TwitterLogoIcon } from "@radix-ui/react-icons";
import { ShoppingCartIcon } from "lucide-react";
import Link from "next/link";

const Footer = () => {

    return (
        <div className="text-zinc-900 font-bold bg-zinc-300 h-32 flex flex-row items-center gap-8 mt-8 ">
            <div className="ml-10 flex flex-row gap-10">
                <div className="flex flex-row gap-10">
                    <ShoppingCartIcon size={"42px"}/>
                    <span className="mt-3">© 2024  </span>
                    <span className="mt-3">Goober e-commerce</span>
                </div>
            
                <div className="absolute end-48 mt-3">
                    <Link href="#">
                        Sobre nós
                    </Link>
                </div>
                <div className="mt-5 absolute end-32">
                    <Link href="https://github.com/l-furquim/Goober">
                            <GitHubLogoIcon/>
                    </Link>
                </div>
                <div className="mt-5 absolute end-20">
                    <Link href="#">
                        <TwitterLogoIcon/>
                    </Link>
                </div>
                <div>
                    <ThemeButton/>
                </div>
            </div>
        </div>
    )
}

export default Footer;