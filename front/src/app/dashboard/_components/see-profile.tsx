import { Button } from "@/components/ui/button";
import { UserIcon } from "lucide-react";
import Link from "next/link";
import { useRouter } from "next/navigation";



type SeeProfileProps = {
  userName: String
};


export default function SeeProfile({userName}: SeeProfileProps){
  const router = useRouter();
  const goToProfile = () => {
      router.push(`/view/user/${userName}`)
  }
  
  return (
  <div className="w-full flex justify-center">
          <Button onClick={goToProfile} className="w-full bg-zinc-300 text-black hover:bg-zinc-400 hover:cursor-pointer rounded-md justify-center">
              <UserIcon size={15}/> Perfil
          </Button>
  </div>
  )
}