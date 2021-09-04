const getProductsByNameContaining = async(name) => {
	const data = await fetch(`/menu/search?name=${name}`);
	if(data.status !== 200) {
		return {};
	} else {
		return data.json();
	}
}

const getProductByName = async(name) => {
	const data = await fetch(`/menu/search/${name}`);
	if(data.status !== 200) {
		return {};
	} else {
		return data.json();
	}
}