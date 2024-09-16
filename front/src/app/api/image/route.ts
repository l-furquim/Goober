import { NextResponse } from 'next/server';
import formidable from 'formidable';
import path from 'path';
import fs from 'fs';

// Configuração para desativar o bodyParser padrão do Next.js
export const config = {
  api: {
    bodyParser: false,
  },
};

// Define a função handler para a rota
export async function POST(request: Request) {
  const uploadDir = path.join(process.cwd(), 'public', 'images');

  const form = new formidable.IncomingForm({
    uploadDir,
    keepExtensions: true,
  });

  return new Promise((resolve) => {
    form.parse(request, (err, fields, files) => {
      if (err) {
        console.error('Erro ao fazer upload do arquivo:', err);
        return resolve(NextResponse.json({ message: 'Erro ao fazer upload do arquivo' }, { status: 500 }));
      }

      const file = files.file?.[0];
      if (!file) {
        return resolve(NextResponse.json({ message: 'Nenhum arquivo enviado' }, { status: 400 }));
      }

      // Caminho do arquivo salvo
      const filePath = path.relative(uploadDir, file.filepath);
      return resolve(NextResponse.json({ filePath: `/images/${filePath}` }));
    });
  });
}
