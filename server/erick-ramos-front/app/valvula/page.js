'use client'

import React, {useEffect, useState} from "react";
import ModalValvula from "../../components/ModalValvula";
import Api from "../../services/api";
import Navbar from "../../components/Navbar";

export default function Valvula() {
    const [valvulas, setValvulas] = useState([]);

    const username = localStorage.getItem('username');
    const accessToken = localStorage.getItem('accessToken');

    const adicionarRetentoresValvula = (novoSelo) => {
        setValvulas([...valvulas, novoSelo]);
    };

    const handleSave = async (formData) => {
        try {
            const response = await Api.post('/valvula', formData, {
                headers: {
                    Authorization: `Bearer ${accessToken}`
                },
            }); // Substitua pelo endpoint correto da sua API
            adicionarRetentoresValvula(response.data);
            closeModal();
        } catch (error) {
            console.error(error);
        }
    };

    async function fetchMoreBooks() {
        const response = await Api.get('valvula', {
            headers: {
                Authorization: `Bearer ${accessToken}`
            },
        })
        setValvulas(response.data);
    }

    useEffect(() => {
        fetchMoreBooks();
    }, []);

    const [isModalOpen, setIsModalOpen] = useState(false);

    const openModal = () => {
        setIsModalOpen(true);
    };

    const closeModal = () => {
        setIsModalOpen(false);
    };

    return (
        <div>
            <Navbar />
            <div className="flex items-center bg-white justify-center h-screen">
                <div className="max-w-2xl w-full p-8 bg-neutral-500 rounded-lg shadow-md text-center">
                    <h1 className="text-2xl font-semibold mb-4">Valvulas</h1>

                    {/* Tabela */}
                    <table className="w-full">
                        <thead>
                        <tr>
                            <th className="py-2 text-center px-4 border-b">Retentor</th>
                            <th className="py-2 text-center px-4 border-b">Preco</th>
                            <th className="py-2 text-center px-4 border-b">Qtd Estoque</th>
                            <th className="py-2 text-center px-4 border-b">Tipo</th>
                            <th className="py-2 text-center px-4 border-b">Codigo</th>
                        </tr>
                        </thead>
                        <tbody>
                        {valvulas.map((retentor) => (
                            <tr key={retentor.id}>
                                <td className="py-2 text-center px-4 border-b">{retentor.nome}</td>
                                <td className="py-2 text-center px-4 border-b">R$ {retentor.preco}</td>
                                <td className="py-2 text-center px-4 border-b">{retentor.qtdEstoque}</td>
                                <td className="py-2 text-center px-4 border-b">{retentor.tipo}</td>
                                <td className="py-2 text-center px-4 border-b">{retentor.codigo}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>

                    <button
                        className="bg-blue-500 text-white rounded py-2 px-4 mb-4 mt-6"
                        onClick={openModal}
                    >
                        Salvar Novo
                    </button>
                    {/* ModalValvula */}
                    {isModalOpen && (
                        <ModalValvula closeModal={closeModal} handleSave={handleSave} />
                    )}
                </div>
            </div>
        </div>
    );
}