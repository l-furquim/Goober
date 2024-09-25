import { GitHubLogoIcon, TwitterLogoIcon } from "@radix-ui/react-icons";
import Link from "next/link";

const Footer = () => {

    return (    
        <footer className="text-white flex flex-row absolute end-20 mt-10 text-muted-foreground gap-15">
            <div>
                <Link href="#">
                    Sobre nós
                </Link>
            </div>
            <div>
                <Link href="#">
                    <GitHubLogoIcon/>
                </Link>
            </div>
            <div>
                <TwitterLogoIcon/>
            </div>
        </footer>
    )

}

export default Footer;