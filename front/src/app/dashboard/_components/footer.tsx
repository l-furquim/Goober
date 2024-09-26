import { GitHubLogoIcon, TwitterLogoIcon } from "@radix-ui/react-icons";
import Link from "next/link";

const Footer = () => {

    return (
        <div className="text-white font-thin  flex flex-row absolute end-32 gap-8 mt-8 mb-10 text-muted-foreground">
            <div>
                <Link href="#">
                    Sobre nós
                </Link>
            </div>
            <div className="mt-1">
                <Link href="https://github.com/l-furquim/Goober">
                    <GitHubLogoIcon/>
                </Link>
            </div>
            <div className="mt-1">
                <Link href="#">
                    <TwitterLogoIcon/>
                </Link>
            </div>
        </div>
    )
}

export default Footer;