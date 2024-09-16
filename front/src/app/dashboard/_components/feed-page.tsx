import Image from "next/image";
import Link from "next/link";

const FeedPage = () => {

    const list = [
        'lucasheqwhn iuqwhcewqhe qwohwq', 'jorge', '233uqowieuiwqoewq', 
        'qweuwqhewqehwqiuh', '123qw22232', 'item 3'];

    return (
        <div className="container flex flex-wrap justify-center ml-10 mt-10 bg-zinc-900 w-full gap-10 h-fit">
            {list.map((item, index) => (
                <ul
                    key={index}
                    className="flex h-72 w-1/4 mt-20  max-w-[calc(100%/3-10px)] rounded-xl bg-zinc-600 list-none"
                >
                <Link href={`/view/product?name=${item}&price=120&category=gamer`}>
                    <li className="p-4">
                        {item} 
                        <Image 
                        width={"600"} height={"300"} src={"/images/foto morangao.png"} alt="Mouse gamer" 
                        style={{ transform: 'scale(0.7)' }}/>
                        <p className="text-2xl font-bold" >R$10,00</p>
                        {/* <p>{ `12 vezes de: ${(100 / 12)}`}</p> */}
                    </li>
                </Link>
                </ul>
            ))}
        </div>
    );

}

export default FeedPage;