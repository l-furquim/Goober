import { Button } from "@/components/ui/button"
import { PencilIcon } from "lucide-react"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import {
  Sheet,
  SheetClose,
  SheetContent,
  SheetDescription,
  SheetFooter,
  SheetHeader,
  SheetTitle,
  SheetTrigger,
} from "@/components/ui/sheet"
import React from "react"
type userEmailProps = {
    userEmail: String
};


const ChangeEmail: React.FC<userEmailProps> =  ({userEmail}) => {
    return (
<div>
        <Sheet>
      <SheetTrigger asChild>
      <Button className="bg-zinc-300 text-black hover:bg-zinc-400" size={"sm"}>
                            Editar <PencilIcon/>
    </Button>
      </SheetTrigger>
      <SheetContent className="bg-zinc-950 text-zinc-300">
        <SheetHeader>
          <SheetTitle className="text-zinc-300">Mudar email</SheetTitle>
          <SheetDescription>
            Tenha certeza da escolha do email
          </SheetDescription>
        </SheetHeader>
        <div className="grid gap-4 py-4">
          <div className="grid grid-cols-4 items-center gap-4">
            <Label htmlFor="name" className="text-right">
              Email atual
            </Label>
            <Input id="name" readOnly value={userEmail.toString()} className="col-span-3 text-muted-foreground" />
          </div>
          <div className="grid grid-cols-4 items-center gap-4">
            <Label htmlFor="username" className="text-right">
              Novo Email
            </Label>
            <Input id="username" className="col-span-3" />
          </div>
        </div>
        <SheetFooter>
          <SheetClose asChild>
            <Button className="bg-zinc-300 text-black hover:bg-zinc-400" type="submit">Salvar mudanças</Button>
          </SheetClose>
        </SheetFooter>
      </SheetContent>
    </Sheet>
</div>

    )
}
export default ChangeEmail;