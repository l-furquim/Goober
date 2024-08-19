import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";

export default function LoginPage() {

    return (

        <div className="h-screen flex   items-center">

            <div className="container max-w-sm rounded-xl bg-violet-700">
            <form>

                <label>
                <Input type="text" /> 
                </label>

                <label>
                <Input type="text" />
                </label>

                <label>
                <Input type="text" />
                </label>

                <Button type="submit" ></Button>

            </form>
            </div>

        </div>


    )


}